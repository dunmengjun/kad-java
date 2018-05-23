package com.dun.dht.kad.network.netty;

import com.dun.dht.kad.utils.UUIDUtil;

import java.io.Serializable;

/**
 * @author mjdun
 * @date 2018/5/23
 * @description
 */
public class Request implements Serializable {

    protected String callId;

    private byte[] data;

    public Request(){

    }

    public Request(byte[] data){
        this.callId = UUIDUtil.uuid();
    }

    public String getCallId() {
        return callId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
