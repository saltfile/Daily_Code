package org.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-pri.xml");
        context.start();
        new CountDownLatch(1).await();
    }
}
