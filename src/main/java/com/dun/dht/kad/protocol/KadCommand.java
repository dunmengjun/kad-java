package com.dun.dht.kad.protocol;

/**
 *
 */
public abstract class KadCommand {

    public abstract KadBaseInfo targetBaseInfo();

    public abstract byte[] bytes();



}
