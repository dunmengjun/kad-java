package com.dun.dht.kad.network;

import com.dun.dht.kad.utils.UUIDUtil;

import java.io.Serializable;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public class Request<T> extends NetworkData<T> {

    public Request(T data) {
        super.setId(UUIDUtil.uuid());
        super.setData(data);
    }
}
