package com.dun.dht.kad.network.netty;

import com.dun.dht.kad.network.NetworkAccpetHandler;
import com.dun.dht.kad.utils.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.util.Map;

public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private NetworkAccpetHandler dataCallback = null;

    private Map<String,Response> map;


    public UdpServerHandler(Map<String,Response> map){
        this.map = map;
    }

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
            Request temp = SerializationUtil.asObject(buffer);
            String callId = temp.getCallId();
            Response response = map.get(callId);
            if(response != null){
                response.setData(temp.getData());
                response.getCountDownLatch().countDown();
                return;
            }
            dataCallback.accpet(msg.sender(),temp.getData());
        }
    }
}
