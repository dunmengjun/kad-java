package com.dun.dht.kad.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public class NettyNetworkServer implements NetworkServer{

    private Logger logger = LoggerFactory.getLogger(NettyNetworkServer.class);

    private ServerBootstrap bootstrap;

    private NettyNetworkServerHandler handler;

    private EventLoopGroup group;

    public NettyNetworkServer(){
        group = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        handler = new NettyNetworkServerHandler();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(handler);
    }

    @Override
    public void start(int port) {
        try {
            bootstrap.bind(port).sync();
            logger.info("server is start...");
        } catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    @Override
    public void handler(NetworkHandler handler) {
        this.handler.setHandler(handler);
    }

    @Override
    public void stop() {
        group.shutdownGracefully();
        logger.info("server is stop...");
    }
}
