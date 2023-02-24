package org.example.ThreadPools;

import java.sql.Time;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadPoolsa {
    public static void main(String[] args) {
        //它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序。
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            singleThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    Thread.currentThread().setName("测试线程");
                    System.out.println("------------" + Thread.currentThread().getName() + " : " + Thread.currentThread().getId());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        singleThreadPool.shutdown();
    }
}
