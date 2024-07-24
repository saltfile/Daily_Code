package org.example.ThreadPools;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThTest {
    private volatile static int num = 0;
    private static synchronized void  add(){
        num++;
    }
    public static void increase() {
        sum.getAndIncrement();
    }
    private static AtomicInteger sum = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int P = 0; P < 5; P++) {
            pool.submit(()->{

                for (int i = 0; i < 500; i++) {
                    increase();
                }
            });
        }


        Thread.sleep(1000);
        System.out.println(sum);

    }
}
