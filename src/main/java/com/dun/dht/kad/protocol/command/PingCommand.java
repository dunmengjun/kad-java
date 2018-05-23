package com.dun.dht.kad.protocol.command;

import com.dun.dht.kad.protocol.KadBaseInfo;
import com.dun.dht.kad.protocol.KadCommand;

/**
 * @author mjdun
 * @date 2018/5/23
 * @description
 */
public class PingCommand extends KadCommand {

    private KadBaseInfo ownBaseInfo;

    private KadBaseInfo targetBaseInfo;


    public PingCommand(KadBaseInfo owner,KadBaseInfo ping) {
        this.ownBaseInfo = owner;
        this.targetBaseInfo = ping;
    }

    @Override
    public KadBaseInfo targetBaseInfo() {
        return this.targetBaseInfo;
    }
}
