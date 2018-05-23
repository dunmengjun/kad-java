package com.dun.dht.kad.protocol;

import com.dun.dht.kad.network.Network;
import com.dun.dht.kad.utils.SerializationUtil;

/**
 * @author mjdun
 * @date 2018/5/23
 * @description
 */
public class DefaultKadDataProtocol implements KadDataProtocol {

    private Network network;


    private static KadDataProtocol instance = new DefaultKadDataProtocol();

    private DefaultKadDataProtocol(){
        network = Network.getInstance();
    }

    public static KadDataProtocol getInstance(){
        return instance;
    }

    @Override
    public KadCommandResult call(KadCommand command) {
        KadBaseInfo kadBaseInfo = command.targetBaseInfo();
        byte[] result = network.send(kadBaseInfo.getInetSocketAddress(), SerializationUtil.asBytes(command));
        KadCommandResult decode = SerializationUtil.asObject(result);
        return decode;
    }

    @Override
    public void accpetCommand(KadCommandAccpeter kadCommandAccpeter) {
        network.accpetHandler((inetSocketAddress, bytes) -> kadCommandAccpeter.accpet(SerializationUtil.asObject(bytes)));
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
}
