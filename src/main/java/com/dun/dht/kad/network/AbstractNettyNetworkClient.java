package com.dun.dht.kad.network;

import com.dun.dht.kad.utils.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author mjdun
 * @date 2018/5/24
 * @description
 */
public abstract class AbstractNettyNetworkClient implements NetworkClient{

    public static ByteBuf toByteBuf(Request request){
        byte[] bytes = SerializationUtil.asBytes(request);
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(bytes.length, bytes.length);
        buffer.writeBytes(bytes);
        return buffer;
    }

    public static ByteBuf toByteBuf(Response request){
        byte[] bytes = SerializationUtil.asBytes(request);
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(bytes.length, bytes.length);
        buffer.writeBytes(bytes);
        return buffer;
    }
}
