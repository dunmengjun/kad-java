package com.dun.dht.kad.network;

import com.dun.dht.kad.utils.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public class NettyNetworkServerHandler extends ChannelInboundHandlerAdapter {

    private NetworkHandler handler;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        Request request = SerializationUtil.asObject(bytes);
        handler.handle(new NettyResponseNetworkClient(ctx.channel()),request);
    }

    public void setHandler(NetworkHandler handler) {
        this.handler = handler;
    }
}
