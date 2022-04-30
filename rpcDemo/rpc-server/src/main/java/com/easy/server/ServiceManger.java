package com.easy.server;

import com.easy.common.utils.ReflectionUtils;
import com.easy.lcrpc.Request;
import com.easy.lcrpc.ServiceDescriptor;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceManger {
    private Map<ServiceDescriptor,ServiceInstance> service;


    public ServiceManger(){
        this.service = new ConcurrentHashMap<>();
    }
    //将所有的interface中的method当成服务注册在这里
    public <T>void register(Class<T> interfaceClass,T bean){
        Method[] methods = ReflectionUtils.getPubilcMethod(interfaceClass);
        for(Method method : methods){
            ServiceInstance sis = new ServiceInstance(bean,method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass,method);
            service.put(sdp,sis);
            System.out.println("register service:"+sdp.getClazzName()+"   "+sdp.getMethod());
        }
    }

    public ServiceInstance lookup(Request request){
        ServiceDescriptor sdp = request.getService();
        return service.get(sdp);
    }

}
