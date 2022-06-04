package lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author saltfish
 * @date 22-06-03
 * 这是简要说明了一个乐观锁的实现
 */
public class Optimistic_Lock {


    private static volatile int v1 = 0;
    static AtomicInteger v2 = new AtomicInteger();
    private static volatile  int v3 = 0;
    public static synchronized void add(){
        v3++;
    }


    public static void main(String[] args) throws InterruptedException {
        int len = 1000;
        for (int i = 0; i < len; i++) {
          new Thread(() -> {
                try {
                        Thread.sleep(100);
                        //普通线程间加值
                        v1++;
                        //使用getAndIncrement函数进行自增操作
                        v2.getAndIncrement();
                        //使用悲观锁sync
                        add();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(100);
        System.out.println("普通的加："+v1);
        System.out.println("乐观锁加："+v2);
        System.out.println("悲观锁加："+v3);

    }







}
