package com.dun.dht.kad.network;

import java.io.Serializable;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public abstract class NetworkData<T> implements Serializable {

    private String id;

    private T data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
