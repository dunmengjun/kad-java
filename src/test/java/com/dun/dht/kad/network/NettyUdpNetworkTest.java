package com.dun.dht.kad.network;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class NettyUdpNetworkTest {

    private Network network = NettyUdpNetwork.getInstance();

    @Test
    public void sendData() throws UnsupportedEncodingException, InterruptedException {
        network.applyAccpetCallback((ip,port,bytes) -> {
            Assert.assertEquals(new String(bytes),"hello world");
        });
        network.sendData("127.0.0.1",9999,"hello world".getBytes("UTF-8"));
//        network.sendData("127.0.0.1",9999,"hello world1".getBytes("UTF-8"));
        Thread.sleep(1000);
    }

    @Test
    public void applyAccpetCallback() {
    }
}
