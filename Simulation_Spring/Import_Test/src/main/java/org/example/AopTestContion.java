package org.example;

import org.example.saltfish.annotations.Aspects;
import org.example.saltfish.annotations.Component;
import org.example.saltfish.annotations.Pointcut;
import org.example.saltfish.aop.Advice;

import java.lang.reflect.Method;

@Component
@Aspects
public class AopTestContion implements Advice {



    @Pointcut(ClassPattern = "org.example",methodPattern = ".*Service")
    @Override
    public Object invoke(Object target, Method method, Object[] arg) throws Exception {
        System.out.println("----------------Befor-------------");
        Object o = method.invoke(target,arg);
        System.out.println("----------------After-------------");
        return o;
    }
}
