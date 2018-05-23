package com.dun.dht.kad.protocol;

import java.net.InetSocketAddress;

/**
 * @author mjdun
 * @date 2018/5/23
 * @description
 */
public class KadBaseInfo {

    private byte[] id;

    private String ip;

    private int port;

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetSocketAddress getInetSocketAddress(){
        return new InetSocketAddress(ip,port);
    }
}
