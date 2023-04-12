package org.example;

import org.example.api.DoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-com.xml");
        DoService service = (DoService) context.getBean("DoService");
        System.out.println( service.SayHello("dubbo"));
        System.in.read();
        System.exit(0);
    }
}
