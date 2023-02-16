package org.example.saltfish.aop;

import org.example.saltfish.aop.Aspect;
import org.omg.CORBA.portable.InvokeHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AopInvokeHandler implements InvocationHandler {

    private Object target;
    private Aspect aspect;

    public AopInvokeHandler(Object target, Aspect aspect) {
        this.target = target;
        this.aspect = aspect;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return this.aspect.getAdvice().invoke(target,method,args);
    }
}
