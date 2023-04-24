package org.example;


import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

class Caitry{
    public int num1 = 100;
}



class Person implements Runnable{

    private Caitry caitry = null;
    private ReentrantLock lock = null;
    public Person(Caitry caitry,ReentrantLock lock){
        this.caitry = caitry;
        this.lock = lock;
    }

    @Override
    public void run() {
        try{

            while (this.caitry.num1 > 1){
            lock.lock();
            caitry.num1--;
            System.out.println(caitry.num1);
            lock.unlock();
//            Thread.sleep(1000*new Random().nextInt(5));
        }

        }catch (Exception o){
            o.printStackTrace();
        }
    }
}








public class ManyCker {
    public static void main(String[] args) {
        Caitry caitry =  new Caitry();
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(new Person(caitry,lock));
        Thread t2 = new Thread(new Person(caitry,lock));
        t1.start();
        t2.start();
    }
}
