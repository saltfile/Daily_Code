package com.easy.lcrpc;

import lombok.Data;

/**
 * 表示RPC请求
 */
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] params;
}
