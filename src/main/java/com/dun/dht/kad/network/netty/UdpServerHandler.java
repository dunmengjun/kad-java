package com.dun.dht.kad.network.netty;

import com.dun.dht.kad.network.NetworkAccpetHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.net.InetSocketAddress;

public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private NetworkAccpetHandler dataCallback = null;

    public void setDataCallback(NetworkAccpetHandler dataCallback) {
        this.dataCallback = dataCallback;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) {
        // 因为Netty对UDP进行了封装，所以接收到的是DatagramPacket对象。
        ByteBuf content = msg.content();
        if(dataCallback != null){
            int size = content.readableBytes();
            byte[] buffer = new byte[size];
            content.readBytes(buffer);
            InetSocketAddress sender = msg.sender();
            dataCallback.accpet(msg.sender(),buffer);
        }
//        if ("hello!!!".equals(req)) {
//            ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(
//                    "结果：", CharsetUtil.UTF_8), msg.sender()));
//        }
    }
}
