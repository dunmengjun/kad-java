package com.dun.dht.kad.network;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public class MockEmbeddedChannel extends EmbeddedChannel {

    public MockEmbeddedChannel(ChannelHandler... handlers) {
        super(handlers);
    }

    @Override
    public ChannelFuture writeAndFlush(Object msg) {
        this.writeInbound(msg);
        return newSucceededFuture();
    }
}
