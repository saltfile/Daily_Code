package com.easy.server;

import com.easy.TranPort.RequsetHandler;
import com.easy.TranPort.TransServer;
import com.easy.common.utils.ReflectionUtils;
import com.easy.lcrpc.Request;
import com.easy.lcrpc.Response;
import com.easy.rpc.Decoder;
import com.easy.rpc.Encoder;
import sun.misc.IOUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class RpcServer {
    private RpcSerConfig config;
    private TransServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManger serviceManger;
    private ServiceInvoke serviceInvoke;
    private RequsetHandler handler = new RequsetHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream ToRespone){
            Response resp = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(recive,recive.available(),true);
                Request req = decoder.decode(inBytes,Request.class);
                System.out.println("get request :"+req);
                ServiceInstance sis = serviceManger.lookup(req);
                Object ret = serviceInvoke.invoke(sis,req);
                resp.setData(ret);

            } catch (IOException e) {
                e.printStackTrace();
                resp.setData(1);
                resp.setMessage("RpcServer got error : "+e.getMessage());
            } finally {
                byte[] outByte = encoder.encode(resp);
                try {
                    ToRespone.write(outByte);
                    System.out.println("respone cilent");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    public RpcServer(){
        this(new RpcSerConfig());
    }
    public RpcServer(RpcSerConfig config) {
        this.config = config;
        this.net = ReflectionUtils.newInstance(config.getTranClass());
        this.net.init(config.getPort(),handler);
        this.encoder = ReflectionUtils.newInstance(config.getEcode());

        this.decoder = ReflectionUtils.newInstance(config.getDcode());

        //service
        this.serviceManger = new ServiceManger();
        this.serviceInvoke = new ServiceInvoke();
    }
    public <T>void register(Class<T> interfaceClass,T bean){
        serviceManger.register(interfaceClass,bean);
    }

    public void start(){
        this.net.start();
    }
    public void stop(){
        this.net.stop();
    }


}
