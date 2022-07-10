package com.saltfish.cloud;

import com.netflix.config.DynamicIntProperty;
import com.netflix.config.DynamicPropertyFactory;

import java.util.concurrent.TimeUnit;

public class ArchalusTest {
    public void SpingConfig(){
        try{
            DynamicIntProperty myAge = DynamicPropertyFactory.getInstance().getIntProperty("my.age", 18);
            System.out.println(myAge);
            System.out.println(myAge.get());
//            TimeUnit.SECONDS.sleep(80);
            System.out.println("动态修改后的值为：");
            System.out.println(myAge);
            System.out.println(myAge.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
