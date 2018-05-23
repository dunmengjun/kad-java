package com.dun.dht.kad.network.netty;

import java.util.concurrent.CountDownLatch;

/**
 * @author mjdun
 * @date 2018/5/23
 * @description
 */
public class Response extends Request{

    private CountDownLatch countDownLatch;

    public Response(String callId, CountDownLatch countDownLatch) {
        this.callId = callId;
        this.countDownLatch = countDownLatch;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
