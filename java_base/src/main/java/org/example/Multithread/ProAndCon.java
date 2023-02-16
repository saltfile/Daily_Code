package org.example.Multithread;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 场景二、生产者消费者
 * 有一个仓库
 * 有多个生产者生产和多个消费者消费
 */
class  Warehouse{
    private Queue<Integer> producs = new ArrayDeque<>();
    private ReentrantLock lock = new ReentrantLock();

    public int Add(){
        lock.lock();
        int num = producs.size()+1;
        producs.offer(num);
        lock.unlock();
        return num;
    }
    public int Take(){
        int res;
        lock.lock();
        if (producs.size() > 0){
         res = producs.poll();
        }
        else
       res = -1;
        lock.unlock();
        return res;
    }
    public int GetSize(){
        return producs.size();
    }
}

class Product implements Runnable{
    private String name;
    private Warehouse warehouse;
    public Product(String name,Warehouse warehouse){
        this.name = name;
        this.warehouse = warehouse;
    }
    @Override
    public void run() {
        try{
        while (true){

            if (this.warehouse.GetSize() > 30){
                continue;
            }

            Random random = new Random();
            Thread.sleep(1000%random.nextInt());
            System.out.println(name+"生产了:"+this.warehouse.Add());
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



class Consumer implements Runnable {

    private String name;
    private Warehouse warehouse;
    public Consumer(String name,Warehouse warehouse){
        this.name = name;
        this.warehouse = warehouse;
    }


    @Override
    public void run() {
        try{

            while (true){
                Random random = new Random();
                while (true) {
                    if (this.warehouse.GetSize() <= 0) {
                        Thread.sleep(100 % random.nextInt());
                    }else {
                        break;
                    }
                }

                System.out.println(name+"消费了:"+this.warehouse.Take());
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}










public class ProAndCon {
    public void CaseStart(){
        Warehouse warehouse = new Warehouse();

        Thread p1 = new Thread(new Product("生产1",warehouse));
        Thread p2 = new Thread(new Product("生产2",warehouse));

        Thread c1 = new Thread(new Consumer("消费1",warehouse));
        Thread c2 = new Thread(new Consumer("消费2",warehouse));
        Thread c3 = new Thread(new Consumer("消费3",warehouse));
        p1.start();
        p2.start();
        c1.start();
        c2.start();
        c3.start();


    }
}
