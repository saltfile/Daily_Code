package org.example.ThreadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheThreadTest {


    /**
      newCachedThreadPool
     1、用来创建一个可以无限扩大的线程池，适用于负载较轻的场景，执行短期异步任务。
     2、可灵活回收空闲线程，若无可回收，则新建线程。
     3、线程池为无限大，当执行第二个任务时第一个任务也已经完成的话，会复用执行第一个任务的线程，而不用每次新建线程。
     */
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    Thread.currentThread().setName("测试线程");
                    System.out.println("------------" + Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
                }
            });
        }
        cachedThreadPool.shutdown();
    }


}
