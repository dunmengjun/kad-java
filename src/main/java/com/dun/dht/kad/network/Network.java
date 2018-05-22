package com.dun.dht.kad.network;

public interface Network {

    void sendData(String ip,int port, byte[] data) throws InterruptedException;

    void applyAccpetCallback(NetworkDataCallback callback);
}
