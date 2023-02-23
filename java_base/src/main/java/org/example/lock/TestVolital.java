package org.example.lock;

import java.util.concurrent.TimeUnit;

public class TestVolital {
    static volatile boolean flag = true;
    public static void main(String[] args) {

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t -----come in");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = false;
        },"t1").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t -----come in");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName()+"\t -----flag被设置为false，程序停止");
        },"t2").start();
    }
}
