package com.dun.dht.kad.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public class NettyNetworkClient extends AbstractNettyNetworkClient {

    private Logger logger = LoggerFactory.getLogger(NettyNetworkClient.class);

    private Channel channel;

    private InetSocketAddress address;

    private Map<String,Response> map;

    public NettyNetworkClient(InetSocketAddress address){
        this.address = address;
        EventLoopGroup group = new NioEventLoopGroup();
        this.map = new ConcurrentHashMap<>();
        NettyNetworkClientHandler nettyNetworkClientHandler = new NettyNetworkClientHandler(map);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(nettyNetworkClientHandler);
            channel = bootstrap.connect(address).channel();
        } catch (Exception e) {
            logger.error(e.getMessage());
            group.shutdownGracefully();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response send(Request request) {
        Response response = new Response(request);
        map.put(request.getId(),response);
        try {
            ByteBuf byteBuf = toByteBuf(request);
            ChannelFuture channelFuture = channel.writeAndFlush(byteBuf);
            response.getCountDownLatch().await(10000,TimeUnit.MILLISECONDS);
            if(channelFuture != null
                    && (!channelFuture.isDone() || !channelFuture.isSuccess())
                    ){
                throw new RuntimeException("request is error");
            }
            if(response.getCountDownLatch().getCount() > 0){
                throw new RuntimeException("request is timeout");
            }
            return response;
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Map<String, Response> getMap() {
        return map;
    }
}
