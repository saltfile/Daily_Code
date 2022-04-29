package com.easy.rpc;

import com.alibaba.fastjson.JSON;

/**
 * 基于json序列化实现
 */
public class JSONEncoder implements Encoder{
    @Override
    public byte[] encode(Object obj) {

        return JSON.toJSONString(obj).getBytes();
    }
}
