package com.dun.dht.kad.protocol;

import com.dun.dht.kad.network.Network;

/**
 * @author mjdun
 * @date 2018/5/23
 * @description
 */
public class DefaultKadDataProtocol implements KadDataProtocol {

    private Network network = Network.getInstance();

    @Override
    public KadCommandResult call(KadCommand command) throws InterruptedException {
        KadBaseInfo kadBaseInfo = command.targetBaseInfo();
        byte[] result = network.send(kadBaseInfo.getInetSocketAddress(), command.bytes());
        KadCommandResult decode = KadResultUtil.decode(result);
        return decode;
    }

    @Override
    public void accpetCommand(KadCommandAccpeter kadCommandAccpeter) {
        network.accpetHandler((inetSocketAddress,bytes) -> kadCommandAccpeter.accpet(KadCommandUtil.decode(bytes)));
    }
}
