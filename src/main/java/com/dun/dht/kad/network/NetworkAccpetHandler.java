package com.dun.dht.kad.network;

import java.net.InetSocketAddress;

/**
 * @author mjdun
 * @date 2018/5/23
 * @description
 */
public interface NetworkAccpetHandler {

    void accpet(InetSocketAddress address,byte[] data);
}
