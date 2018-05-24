package com.dun.dht.kad.network;

import com.dun.dht.kad.utils.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public class NettyNetworkClientHandler extends ChannelInboundHandlerAdapter {

    private Map<String,Response> map;

    public NettyNetworkClientHandler(Map<String, Response> map) {
        this.map = map;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] buffer = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(buffer);
        NetworkData temp = SerializationUtil.asObject(buffer);
        String id = temp.getId();
        Response response = map.get(id);
        if(response == null){
            return;
        }
        response.setData(temp.getData());
        response.getCountDownLatch().countDown();
        return;
    }
}
