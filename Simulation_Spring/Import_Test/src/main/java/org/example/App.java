package org.example;

import org.example.saltfish.custom_Exception.BeanNotFoundException;
import org.example.saltfish.package_scan.ApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws BeanNotFoundException {

        ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);

        userService user = (userService) applicationContext.getBean("userService");
        user.xxx();
    }
}
