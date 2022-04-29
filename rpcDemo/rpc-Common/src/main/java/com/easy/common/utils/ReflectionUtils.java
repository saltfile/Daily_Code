package com.easy.common.utils;
//做反射的类


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ReflectionUtils {
    //根据class创建对象
    public static <T>T newInstance(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }
    //获取某个类的公用方法
    public static Method[] getPubilcMethod(Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        ArrayList<Method> pmethods = new ArrayList<>();
        for(Method m:methods){
            if(Modifier.isPublic(m.getModifiers())){
                pmethods.add(m);
            }
        }
        return pmethods.toArray(new Method[0]);
    }
    //调用指定方法指定对象
    public static Object invoke(Object o,Method method,Object... args) throws InvocationTargetException, IllegalAccessException {
       return method.invoke(o,args);
    }



 }
