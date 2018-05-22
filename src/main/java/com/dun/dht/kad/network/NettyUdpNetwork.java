package com.dun.dht.kad.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * netty实现
 */
public class NettyUdpNetwork implements Network{

    private final Logger logger = LoggerFactory.getLogger(NettyUdpNetwork.class);

    private Channel udpChannel = null;

    private UdpServerHandler udpServerHandler = null;

    private Integer port = 9999;

    private static final Network instance = new NettyUdpNetwork();

    private NettyUdpNetwork(){
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            udpServerHandler = new UdpServerHandler();
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
        }
    }


    public static final Network getInstance(){
        return instance;
    }

    @Override
    public void sendData(String ip,int port, byte[] data) {
        udpChannel.writeAndFlush(
                new DatagramPacket(Unpooled.copiedBuffer(data),
                        new InetSocketAddress(ip,port))
        );
    }

    @Override
    public void applyAccpetCallback(NetworkDataCallback callback) {
        udpServerHandler.setDataCallback(callback);
    }
}
