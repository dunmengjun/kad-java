package com.dun.dht.kad.network;

import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public class NettyNetworkServerHandlerTest {

    @Test
    public void channelRead() {
        NettyNetworkServerHandler nettyNetworkServerHandler = new NettyNetworkServerHandler();
        nettyNetworkServerHandler.setHandler((client,request)->{
            Assert.assertNotNull(client);
            Assert.assertNotNull(request);
            Assert.assertEquals("hello world",request.getData());
        });
        EmbeddedChannel channel = new EmbeddedChannel(
                nettyNetworkServerHandler
        );

        channel.writeInbound(AbstractNettyNetworkClient.toByteBuf(new Request("hello world")));
    }
}
