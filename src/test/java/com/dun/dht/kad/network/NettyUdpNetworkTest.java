package com.dun.dht.kad.network;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

public class NettyUdpNetworkTest {

    private Network network = Network.getInstance();

    @Test
    public void sendData() throws UnsupportedEncodingException, InterruptedException {

        byte[] send = network.send(new InetSocketAddress("127.0.0.1", 9999), "hello world".getBytes("UTF-8"));

        Thread.sleep(1000);
    }

    @Test
    public void applyAccpetCallback() {
    }
}
