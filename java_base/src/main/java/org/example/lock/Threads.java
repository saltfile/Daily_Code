package org.example.lock;
class CallableDemo implements Runnable {
    /**
     * 3----yield
     * @author Sure
     * @since 2021/2/25 11:50
     * @param null :
     * @return null
     */
    private String name;

    public CallableDemo(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程运行开始");
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName()+"  "+this.getName()+"======"+i);
            if (i==20){
                Thread.yield();
            }

        }

    }

}
public class Threads {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"主线程运行开始");
        Thread c = new Thread(new CallableDemo("C"));
        Thread d = new Thread(new CallableDemo("D"));
        c.start();
        d.start();
        try {
            c.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        try {
            d.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"主线程运行结束");
    }
}
