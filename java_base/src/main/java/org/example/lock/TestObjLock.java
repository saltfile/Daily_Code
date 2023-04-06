package org.example.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


class Tresf implements Runnable {
    Object object;

    public Tresf(Object obj) {
        this.object = obj;
    }

    @Override
    public void run() {
        try {
            synchronized (object) {

                Thread.sleep(5000);

                System.out.println("======================");
                System.out.println(ClassLayout.parseInstance(object).toPrintable());
                System.out.println("======================");
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}





public class TestObjLock {
    static final Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
       Thread t1 = new Thread(new Tresf(obj));
       Thread t2 = new Thread(new Tresf(obj));
       Thread t3 = new Thread(new Tresf(obj));
       t1.start();
       t2.start();
       t3.start();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        t1.join();
        t2.join();
        t3.join();
        System.out.println("********************************************************");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        System.gc();

        System.out.println(ClassLayout.parseInstance(obj).toPrintable());



    }

}
