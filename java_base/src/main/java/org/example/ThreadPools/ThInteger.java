package org.example.ThreadPools;

import java.util.Random;
import java.util.concurrent.Executors;

public class ThInteger implements Runnable{
    private static final ThreadLocal<Integer> a = ThreadLocal.withInitial(()->new Integer(0));


    public static void main(String[] args) throws InterruptedException {
        ThInteger obj = new ThInteger();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(obj);
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }




    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "+1");
            Thread.sleep(new Random().nextInt(1000));
            Integer as = a.get();
            a.set(as + 1);
            System.out.println(Thread.currentThread().getName()+"     "+a.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
