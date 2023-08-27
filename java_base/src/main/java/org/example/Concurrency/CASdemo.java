package org.example.Concurrency;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
class CasExample {
    private volatile int state = 0;
    private static final Unsafe unsafe;
    private static final long statOffset;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            statOffset = unsafe.objectFieldOffset(CasExample.class.getDeclaredField("state"));
        }  catch (Exception ex) { throw new Error(ex); }
    }

    CasExample(int state){
        this.state = state;
    }

    public void doSomeThing() throws InterruptedException {
        Thread.sleep(2000);
        if (unsafe.compareAndSwapInt(this,statOffset,0,1)){
            this.state = 1;
            System.out.println(Thread.currentThread().getId()+"  "+state);
        }else {
            this.state = 0;
            System.out.println(Thread.currentThread().getId()+" a "+state);
        }

    }
}

class NoCasExample{
    private volatile int state = 0;
    public void doSomeThing(){
            if (state == 0){
                state = 1;
            }else {
                state = 0;
            }
        System.out.println(Thread.currentThread().getId()+"  "+state);
    }

}


class NoTest implements Runnable{
    CountDownLatch countDownLatch;

    public NoTest(CountDownLatch latch){
        System.out.println(Thread.currentThread().getId()+"  "+"开启");
        this.countDownLatch = latch;
    }

    @Override
    public void run() {
        try {
            NoCasExample n1 = new NoCasExample();
            n1.doSomeThing();
            Thread.sleep(2000);
            countDownLatch.countDown();
            countDownLatch.await();
        }catch (Exception r){
            r.printStackTrace();
        }

    }
}




class CasTest implements Runnable{
    CountDownLatch countDownLatch;
    int state;

    public CasTest(CountDownLatch latch,int state){
        System.out.println(Thread.currentThread().getId()+"  "+"开启");
        this.countDownLatch = latch;
        this.state = state;
    }

    @Override
    public void run() {
        try {
            CasExample n1 = new CasExample(state);
            n1.doSomeThing();

            countDownLatch.countDown();
            countDownLatch.await();
        }catch (Exception r){
            r.printStackTrace();
        }

    }
}







public class CASdemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
//        for (int i = 0; i < 5; i++) {
//            new Thread(new NoTest(latch)).start();
//        }

        new Thread(new CasTest(latch,1)).start();
        new Thread(new CasTest(latch,0)).start();
        new Thread(new CasTest(latch,1)).start();
        new Thread(new CasTest(latch,1)).start();
        new Thread(new CasTest(latch,0)).start();


        new CasExample(1).doSomeThing();







    }
}
