package com.dun.dht.kad.protocol;

/**
 * @author mjdun
 * @date 2018/5/23
 * @description
 */
public interface KadDataProtocol {

    KadCommandResult call(KadCommand command) throws InterruptedException;

    void accpetCommand(KadCommandAccpeter kadCommandAccpeter);


    static KadDataProtocol getInstance(){
        return DefaultKadDataProtocol.getInstance();
    }
}
