package com.dun.dht.kad.network;

public interface NetworkDataCallback {

    void accpet(String ip,Integer port,byte[] data);
}
