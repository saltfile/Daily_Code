package org.example.lock;




















public class TestLock {

    public static int anInt = 100;

    class TT implements Runnable{
        private SpinLock spinLock;

        public TT(SpinLock spinLock){
            this.spinLock = spinLock;
        }

        @Override
        public void run() {
            try {


                while (true) {
                    if (anInt <= 0) break;
                    spinLock.lock();
                    System.out.println(Thread.currentThread().getName()+"  :"+anInt--);
                    spinLock.unlock();
//                    Thread.sleep(300);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void StartGrep(){
        SpinLock spinLock = new SpinLock();

        Thread t1 = new Thread(new TT(spinLock));
        Thread t2 = new Thread(new TT(spinLock));

        Thread t3 = new Thread(new TT(spinLock));
        Thread t4 = new Thread(new TT(spinLock));
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }


}
