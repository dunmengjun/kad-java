package com.dun.dht.kad.network;

import org.junit.Test;

import java.net.InetSocketAddress;

public class NettyUdpNetworkTest {

    private Network network = Network.getInstance();

    @Test
    public void sendData() {
        try {
            byte[] send = network.send(new InetSocketAddress("127.0.0.1", 9999), "hello world".getBytes("UTF-8"));
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void applyAccpetCallback() {
    }
}
