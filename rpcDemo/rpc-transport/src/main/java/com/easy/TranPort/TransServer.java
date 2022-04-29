package com.easy.TranPort;

/**
 * 1.启动、监听
 * 2.接受请求
 * 3.关闭监听
 * 4.
 */
public interface TransServer {
    void init(int port, RequsetHandler handler);
    void start();
    void stop();
}
