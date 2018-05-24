package com.dun.dht.kad.network;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public class Response<T> extends NetworkData<T> {

    private transient CountDownLatch countDownLatch;

    public Response(){}

    public Response(Request request) {
        setId(request.getId());
        this.countDownLatch = new CountDownLatch(1);
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

}
