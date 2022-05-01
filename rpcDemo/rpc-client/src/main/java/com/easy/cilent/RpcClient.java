package com.easy.cilent;

import com.easy.common.utils.ReflectionUtils;
import com.easy.rpc.Decoder;
import com.easy.rpc.Encoder;

import java.lang.reflect.Proxy;

public class RpcClient {
    private RpcClientConfig  config;
    private Encoder encoder;
    private Decoder decoder;
    private TranportsSelector selector;

    public RpcClient(){
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config){
        this.config = config;

        this.encoder  =  ReflectionUtils.newInstance(this.config.getEcoder());
        this.decoder = ReflectionUtils.newInstance(this.config.getDecoder());
        this.selector = ReflectionUtils.newInstance(this.config.getSelectClass());

        this.selector.init(this.config.getServers(),this.config.getConnectCount(),this.config.getTranClients());
    }

    /**
     * 代理
     * @return
     */
    public <T>T getProxy(Class<T> clazz){
        return (T)Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{clazz},new RemoteInvoker(clazz,encoder,decoder,selector));
    }

}
