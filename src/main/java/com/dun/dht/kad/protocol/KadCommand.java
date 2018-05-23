package com.dun.dht.kad.protocol;

import java.io.Serializable;

/**
 *
 */
public abstract class KadCommand implements Serializable {

    public abstract KadBaseInfo targetBaseInfo();

}
