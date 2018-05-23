package com.dun.dht.kad.network.netty;

import com.dun.dht.kad.network.Network;
import com.dun.dht.kad.network.NetworkAccpetHandler;
import com.dun.dht.kad.utils.SerializationUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * netty实现
 */
public class NettyUdpNetwork implements Network {

    private final Logger logger = LoggerFactory.getLogger(NettyUdpNetwork.class);

    private Channel udpChannel = null;

    private UdpServerHandler udpServerHandler = null;

    private Integer port = 9999;

    private Map<String,Response> map;

    private static Network instance;

    private long timeout = 10000;

    private NettyUdpNetwork() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            map = new ConcurrentHashMap<>();
            udpServerHandler = new UdpServerHandler(map);
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(udpServerHandler);
            udpChannel = bootstrap.bind(port).sync().channel();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                group.shutdownGracefully();
                logger.info("server stop....");
            }));
            logger.info("server start on port 9999.....");
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            group.shutdownGracefully();
            throw new RuntimeException(e);
        }
    }


    public static final Network getInstance(){
        if(instance == null){
            NettyUdpNetwork nettyUdpNetwork = new NettyUdpNetwork();
            if(instance == null){
                instance = nettyUdpNetwork;
            }
        }
        return instance;
    }

    @Override
    public byte[] send(InetSocketAddress address, byte[] data) {
        Request request = new Request(data);
        CountDownLatch downLatch = new CountDownLatch(1);
        Response response = new Response(request.getCallId(),downLatch);
        map.put(request.getCallId(), response);
        ChannelFuture channelFuture = udpChannel.writeAndFlush(
                new DatagramPacket(Unpooled.copiedBuffer(SerializationUtil.asBytes(request)),
                        address)
        );
        try {
            downLatch.await(timeout,TimeUnit.MILLISECONDS);
            map.remove(request.getCallId());
            if(!channelFuture.isSuccess() || !channelFuture.isDone()){
                //如果IO操作都不是成功的,或者没有完成
                throw new RuntimeException("request is not success");
            }
            return response.getData();
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void accpetHandler(NetworkAccpetHandler handler) {
        udpServerHandler.setDataCallback(handler);
    }
}
