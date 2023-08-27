package org.example.lock;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

class tasj implements Runnable{
    Random random=new Random();
    CyclicBarrier barrier = null;
    public tasj(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    //参加会议方法
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" 到达会议室，【等待开会】.......");
        try {
            //挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务。
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class real_task implements Runnable{

    @Override
    public void run() {

            System.out.println("真正的开会任务");

    }
}



public class Cyclicat {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(10, new real_task());
        //创建10个线程，每个线程代表一个人凑齐10个。
        for(int i=0;i<10;i++){
            new Thread(new tasj(barrier)).start();
        }




    }
}
