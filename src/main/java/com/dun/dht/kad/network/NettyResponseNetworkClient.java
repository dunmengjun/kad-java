package com.dun.dht.kad.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public class NettyResponseNetworkClient extends AbstractNettyNetworkClient{

    private Channel channel;

    public NettyResponseNetworkClient(Channel channel){
        this.channel = channel;
    }

    @Override
    public Response send(Request request) {
        Response response = new Response(request);
        ByteBuf byteBuf = toByteBuf(response);
        channel.writeAndFlush(byteBuf);
        return null;
    }
}
