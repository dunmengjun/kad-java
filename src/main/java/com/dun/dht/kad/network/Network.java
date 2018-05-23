package com.dun.dht.kad.network;

import com.dun.dht.kad.network.netty.NettyUdpNetwork;

import java.net.InetSocketAddress;

public interface Network {

    /**
     * 提供调用应答的同步调用模式
     * @param address 网络地址
     * @param data 数据
     * @return 返回的数据
     */
    byte[] send(InetSocketAddress address, byte[] data);

    /**
     * 被调用时设置处理方式
     * @param handler
     */
    void accpetHandler(NetworkAccpetHandler handler);

    /**
     * 默认的获取自身实例方法
     * @return
     */
    static Network getInstance(){
        return NettyUdpNetwork.getInstance();
    }
}
