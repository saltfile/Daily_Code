package lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author saltfish
 * @date 22-06-03
 * 这是简要说明了一个乐观锁的实现
 */
public class Optimistic_Lock {

    static AtomicInteger as = new AtomicInteger();
//    private static volatile int a = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        //使用getAndIncrement函数进行自增操作
                        System.out.println(as.incrementAndGet());
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
    }







}
