package org.example.ThreadPools;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时线程任务池
 */
public class ShuldeThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        //创建线程，延迟5秒执行
//        scheduledThreadPool.schedule(new Runnable() {
//            public void run() {
//                Thread.currentThread().setName("测试线程");
//                System.out.println("------------" + Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
//            }
//        }, 5, TimeUnit.SECONDS);
//        scheduledThreadPool.shutdown();


        //创建线程，延迟5秒执行，并每隔2秒执行一次
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                Thread.currentThread().setName("测试线程");
                System.out.println("------------" + Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
            }
        }, 3, 2, TimeUnit.SECONDS);
        //这里注释掉，否则会关闭线程
//        scheduledThreadPool.shutdown();
    }
}
