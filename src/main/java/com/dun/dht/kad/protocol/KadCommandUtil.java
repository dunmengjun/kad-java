package com.dun.dht.kad.protocol;


import com.dun.dht.kad.protocol.command.PingCommand;

/**
 * @author mjdun
 * @date 2018/5/23
 * @description
 */
public class KadCommandUtil {

    public static final KadCommand pintCommand(KadBaseInfo own,KadBaseInfo ping){
        return new PingCommand(own,ping);
    }
}
