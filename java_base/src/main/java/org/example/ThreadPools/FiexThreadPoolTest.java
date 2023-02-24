package org.example.ThreadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1、创建一个固定大小的线程池，因为采用无界的阻塞队列，所以实际线程数量永远不会变化，适用于负载较重的场景，对当前线程数量进行限制。
 * 2、保证线程数可控，超出的线程会在队列中等待，所以不会造成线程过多，导致系统负载更为严重。
 */
public class FiexThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.execute(new Runnable() {
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
        fixedThreadPool.shutdown();
    }
}
