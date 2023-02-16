package org.example.Multithread;


import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 场景1
 * 有三个站口卖票
 * 一共有一百张票
 */
class Station{
    private int ticket = 100;
    public int SellOut(){
        return ticket--;
    }

    public int getTicket() {
        return ticket;
    }
}




//抢票的
class GrabTickets implements Runnable{
    private Station station;
    private String name;

    private ReentrantLock lock;
    public GrabTickets(Station station,String name,ReentrantLock lock){
        this.station = station;
        this.name = name;
        this.lock = lock;
    }


    @Override
    public void run() {
        try{
            Random random = new Random();
            while (true){
                if (this.station.getTicket() <= 0)break;
                //开始抢票
                Thread.sleep(100*(10%random.nextInt()));
                this.lock.lock();
                System.out.println(name+"抢到票："+this.station.SellOut()+" 号");
                this.lock.unlock();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


public class Tickets {
    public void CaseStart(){
        Station station = new Station();
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(new GrabTickets(station,"小明",lock));
        Thread t2 = new Thread(new GrabTickets(station,"小红",lock));
        Thread t3 = new Thread(new GrabTickets(station,"小王",lock));

        t1.start();
        t2.start();
        t3.start();

    }


}
