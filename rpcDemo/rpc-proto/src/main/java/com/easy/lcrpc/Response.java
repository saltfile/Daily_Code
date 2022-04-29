package com.easy.lcrpc;

import lombok.Data;

/**
 * 表示rpc请求返回
 */
@Data
public class Response {
    //服务返回编码 200成功 非200失败
    private int code;//返回的是状态码
    private String message = "ok";//返回的消息
    private Object data;//返回的东西
}
