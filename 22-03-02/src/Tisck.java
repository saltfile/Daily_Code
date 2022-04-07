import java.sql.Timestamp;
import java.util.Random;

class window implements Runnable{
    private static int ticks = 10;
    private int num = 0;
    private Random random = new Random();
    private Object token = new Object();

    public window(int num){
        this.num = num;
    }
    @Override
    public void run() {
        Thread.currentThread().setName("窗口"+num);
        try {
        while (true){
            synchronized (token){
                if(this.ticks>0){
                   ticks--;
                   Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"卖出一张票"+ticks);
                    token.wait();
                }else {
                    System.out.println(Thread.currentThread().getName()+"窗口关闭");
                  System.exit(0);

                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    }

}






public class Tisck {
    public static void main(String[] args) {
        Thread w1 = new Thread(new window(1));
        Thread w2 = new Thread(new window(2));
        Thread w3 = new Thread(new window(3));
        w1.start();
        w2.start();
        w3.start();

    }

}
