package com.dun.dht.kad.network;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public interface NetworkServer {

    void start(int port);

    void handler(NetworkHandler handler);

    void stop();

}
