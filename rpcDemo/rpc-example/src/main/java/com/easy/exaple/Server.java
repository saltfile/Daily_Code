package com.easy.exaple;

import com.easy.server.RpcServer;

public class Server {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        RpcServer server = new RpcServer();
        server.register(CalcService.class,new ClacServicempl());
        server.start();

    }
}
