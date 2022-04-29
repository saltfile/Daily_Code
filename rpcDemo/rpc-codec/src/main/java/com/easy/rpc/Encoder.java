package com.easy.rpc;

/**
 * 序列化
 */
public interface Encoder {
    byte[] encode(Object obj);
}
