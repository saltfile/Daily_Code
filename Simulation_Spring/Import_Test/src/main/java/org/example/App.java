package org.example;

import org.example.saltfish.aop.Advice;
import org.example.saltfish.aop.Aspect;
import org.example.saltfish.aop.PointCut;
import org.example.saltfish.custom_Exception.BeanNotFoundException;
import org.example.saltfish.package_scan.ApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws BeanNotFoundException {

        ApplicationContext Context = new ApplicationContext(AppConfig.class);
        xxxss ss = (xxxss) Context.getBean("xxxss");
        ss.xxx();
//
        userinterface user = (userinterface) Context.getBean("userService");
        user.xxx();

        testsinterface test  = (testsinterface) Context.getBean("ttService");
        test.tock();

    }
}
