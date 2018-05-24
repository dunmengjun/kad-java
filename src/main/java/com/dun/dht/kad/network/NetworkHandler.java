package com.dun.dht.kad.network;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public interface NetworkHandler {

    void handle(NettyResponseNetworkClient nettyResponseNetworkClient, Request request);
}
