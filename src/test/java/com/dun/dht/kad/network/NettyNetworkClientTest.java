package com.dun.dht.kad.network;

import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Assert;
import org.junit.Test;

import java.net.InetSocketAddress;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public class NettyNetworkClientTest {

    @Test
    public void send() {
        NettyNetworkClient client = new NettyNetworkClient(new InetSocketAddress("127.0.0.1",9999));
        Request<String> request = new Request<>("hello world");
        NettyNetworkClientHandler handler = new NettyNetworkClientHandler(
                client.getMap()
        );
        EmbeddedChannel embeddedChannel = new MockEmbeddedChannel(
                handler
        );
        client.setChannel(embeddedChannel);
        Response response = client.send(request);

        Assert.assertEquals("hello world",response.getData());
        Assert.assertEquals(request.getId(),response.getId());

    }
}
