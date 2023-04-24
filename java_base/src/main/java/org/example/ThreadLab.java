package org.example;

import java.util.Random;

/**
 * 快速写出
 * 有线程t1,t2,t3，当t1,t2执行完之后t3在继续执行
 */

class Td1 implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("我是线程1开始执行");
            Thread.sleep(1000* new Random().nextInt(10));
            System.out.println("线程1执行结束");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
class  Td2 implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("我是线程2始执行");
            Thread.sleep(1000* new Random().nextInt(10));
            System.out.println("线程2执行结束");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}


class Td3 implements Runnable{
    private Thread t1;
    private Thread t2;
    public Td3(Thread t1,Thread t2){
        this.t1 = t1;
        this.t2 = t2;
    }
    @Override
    public void run() {

        try {
            t1.join();
            t2.join();
            System.out.println("我是线程3始执行");
            Thread.sleep(1000* new Random().nextInt(10));
            System.out.println("线程3执行结束");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}




public class ThreadLab {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Td1());
        Thread t2 = new Thread(new Td2());
        Thread t3 = new Thread(new Td3(t1,t2));
        t1.start();
        t2.start();
        t3.start();
    }
}
