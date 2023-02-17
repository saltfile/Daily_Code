package org.example.lock;


import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  锁案例一使用synchronized来完成买票的过程
 */
class Station{
    private int ticket = 30;
    public synchronized int SellOut(){
        return ticket--;
    }

    public synchronized int getTicket() {
        return ticket;
    }
}



class GrabTickets implements Runnable{
    private Station station;
    private String name;

    public GrabTickets(Station station, String name){
        this.station = station;
        this.name = name;
    }


    @Override
    public void run() {
        try{
            Random random = new Random();
            while (true){
                if (this.station.getTicket() <= 0)break;
                //开始抢票
                Thread.sleep(100*(10%random.nextInt()));
                System.out.println(name+"抢到票："+this.station.SellOut()+" 号");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}





public class Sync {
    public void GradStart(){
        Station station = new Station();
        Thread t1 = new Thread(new GrabTickets(station,"抢票1"));
        Thread t2 = new Thread(new GrabTickets(station,"抢票2"));
        Thread t3 = new Thread(new GrabTickets(station,"抢票3"));


        t1.start();
        t2.start();
        t3.start();


    }
}
