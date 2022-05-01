package com.easy.cilent;

import com.easy.TranPort.TransCilent;
import com.easy.lcrpc.Request;
import com.easy.lcrpc.Response;
import com.easy.lcrpc.ServiceDescriptor;
import com.easy.rpc.Decoder;
import com.easy.rpc.Encoder;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务的代理类
 */
public class RemoteInvoker implements InvocationHandler {
     private Class clazz;
     private Encoder encoder;
     private Decoder decoder;
     private TranportsSelector selector;
    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder,TranportsSelector selector){
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder =decoder;
        this.selector = selector;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setService(ServiceDescriptor.from(clazz,method));
        request.setParams(args);

        Response resp = invokeRemote(request);
        if(resp == null || resp.getCode() != 0){
            throw  new IllegalStateException("fail to invoke remote: "+request);
        }


        return resp.getData();
    }

    private Response invokeRemote(Request request){
        TransCilent client = null;
        Response resp = null;
        try{
            client = selector.select();

            byte[] outBytes = encoder.encode(request);
            InputStream revice = client.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(revice, revice.available(),true);
            resp = decoder.decode(inBytes,Response.class);


        } catch (IOException e) {
            e.printStackTrace();
            resp.setCode(1);
            resp.setMessage("RpcClient got error"+e.getClass()+":"+e.getMessage());
        } finally {
            if(client != null){
                selector.release(client);
            }
        }
        return resp;
    }

}
