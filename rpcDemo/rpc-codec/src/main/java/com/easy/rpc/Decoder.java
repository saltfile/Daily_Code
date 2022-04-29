package com.easy.rpc;

public interface Decoder {
    <T>T decode(byte[] bytes,Class<T> clazz);
}
