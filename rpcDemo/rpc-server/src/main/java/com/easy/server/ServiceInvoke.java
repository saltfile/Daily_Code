package com.easy.server;

import com.easy.common.utils.ReflectionUtils;
import com.easy.lcrpc.Request;

import javax.xml.ws.Service;
import java.lang.reflect.InvocationTargetException;

/**
 * 调用具体服务
 */




public class ServiceInvoke {
    public Object invoke(ServiceInstance service, Request request) {
        try {
            return ReflectionUtils.invoke(service.getTarget(),service.getMethod(),request.getParams());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
