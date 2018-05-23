package com.dun.dht.kad.utils;

import org.nustaq.serialization.FSTConfiguration;

/**
 * @author mjdun
 * @date 2018/5/23
 * @description
 */
public class SerializationUtil {

    private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();


    public static byte[] asBytes(Object o){
        return conf.asByteArray(o);
    }

    public static <T>T asObject(byte[] data){
        return (T) conf.asObject(data);
    }
}
