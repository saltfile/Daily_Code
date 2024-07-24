package aohiothor.timeWheel;

import java.util.concurrent.CountDownLatch;

public class TimeTest {
    static int inCount = 0;

    static int runCount = 0;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Timer timer = new Timer();
        for(int i=1;i<=2;i++){
            TimerTask timerTask = new TimerTask(i,()->{
                try{
                countDownLatch.countDown();
                int index = addRun();
                Thread.sleep(3000);
                System.out.println(index+"----------执行");
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            timer.addTask(timerTask);
            System.out.println(i+"++++++++++加入");
            inCount++;
        }
        TimerTask timerTask = new TimerTask(5000,()->{
            countDownLatch.countDown();
            int index = addRun();
            System.out.println(index+"----------执行");
        });
        timer.addTask(timerTask);
        try {
            countDownLatch.await();
            System.out.println("inCount" + inCount);
            System.out.println("runCount" + runCount);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized static int addRun(){
        runCount++;
        return runCount;
    }

}
