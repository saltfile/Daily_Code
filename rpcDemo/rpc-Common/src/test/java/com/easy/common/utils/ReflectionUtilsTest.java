package com.easy.common.utils;

import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class ReflectionUtilsTest extends TestCase {
    @Test
    public void testNewInstance() throws InstantiationException, IllegalAccessException {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);
    }
    @Test
    public void testGetPubilcMethod() {
        Method[] methods = ReflectionUtils.getPubilcMethod(TestClass.class);
        assertEquals(1,methods.length);
        System.out.println(methods[0].getName());
    }

    public void testInvoke() throws InvocationTargetException, IllegalAccessException {
        Method[] methods = ReflectionUtils.getPubilcMethod(TestClass.class);
        Method m = methods[0];
        TestClass testClass = new TestClass();
        System.out.println(ReflectionUtils.invoke(testClass,m));
    }
}