package com.easy.TranPort;

import com.easy.lcrpc.Peer;

import java.io.InputStream;

/**
 * 创建链接
 * 发送数据并且等待响应
 * 关闭连接
 */
public interface TransCilent {
    void connect(Peer peer);
    InputStream write(InputStream input);
    void close();
}
