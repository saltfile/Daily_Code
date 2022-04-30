package com.easy.lcrpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 表示服务
 * @
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazzName;
    private String method;
    private String returnType;
    private String [] parametesType;

    public static ServiceDescriptor from(Class clazz, Method method){
        ServiceDescriptor ser = new ServiceDescriptor();
        ser.setClazzName(clazz.getName());
        ser.setMethod(method.getName());
        ser.setReturnType(method.getReturnType().getName());

        Class[] paramet = method.getParameterTypes();
        String[] paramType = new String[paramet.length];
        for(int i = 0; i < paramet.length;i++){
            paramType[i] = paramet[i].getName();
        }
        ser.setParametesType(paramType);

        return ser;
    }
}
