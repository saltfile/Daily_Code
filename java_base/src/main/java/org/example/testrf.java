package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class testrf {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName("org.example.Tests");
        Method met = clazz.getDeclaredMethod("fun");
        Constructor ul = clazz.getConstructor();;
        Object obj = ul.newInstance();
        met.invoke(obj);



    }
}

