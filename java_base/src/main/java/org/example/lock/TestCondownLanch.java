package org.example.lock;

import java.util.concurrent.CountDownLatch;
class Decrement implements Runnable {

    CountDownLatch countDownLatch;

    public Decrement(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {

            for(long i = countDownLatch.getCount();i > 0;i--){
                Thread.sleep(1000);
                System.out.println("countdown"+countDownLatch.getCount());
                this.countDownLatch.countDown();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class Increment implements Runnable {

    CountDownLatch countDownLatch;

    public Increment(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("await");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waiter Released");

    }
}
public class TestCondownLanch {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        Thread t1 = new Thread(new Increment(latch));
        Thread t2 = new Thread(new Decrement(latch));
        t1.start();
        t2.start();

    }
}
