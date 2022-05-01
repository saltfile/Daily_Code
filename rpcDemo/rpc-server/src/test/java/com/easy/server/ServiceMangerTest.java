package com.easy.server;

import com.easy.common.utils.ReflectionUtils;
import com.easy.lcrpc.Request;
import com.easy.lcrpc.ServiceDescriptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceMangerTest {
    ServiceManger sm;
    @Before
    public void init(){
        sm = new ServiceManger();
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class,bean);
    }
    @Test
    public void register() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class,bean);
    }

    @Test
    public void lookup() {
       Method m =  ReflectionUtils.getPubilcMethod(TestInterface.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class,m);
       Request request = new Request();
        request.setService(sdp);
        ServiceInstance sis = sm.lookup(request);
        assertNotNull(sis);
    }
}