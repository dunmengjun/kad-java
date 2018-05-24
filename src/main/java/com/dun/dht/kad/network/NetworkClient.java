package com.dun.dht.kad.network;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public interface NetworkClient {
    /**
     * 提供调用应答的同步调用模式
     * @param request
     * @return
     */
    Response send(Request request) ;
}
