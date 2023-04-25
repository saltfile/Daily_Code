package org.example;


import java.util.concurrent.*;

class task implements Runnable{
    private int num = 0;
    public task(int num){
        this.num = num;
    }
    @Override
    public void run() {
        try {
        System.out.println("我是线程"+num+"处理线程是"+Thread.currentThread().getId());

            Thread.sleep(1000*3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class task_futer implements Callable<String>{
    private int num;
    public task_futer(int num){
        this.num = num;
    }

    @Override
    public String call() throws Exception {
        try {
            String res = "我是线程" + num + "处理我的线程是" + Thread.currentThread().getName();
            Thread.sleep(1000*3);
            return res;
        }catch (Exception o){
            o.printStackTrace();
        }
        return null;
    }
}


public class PoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService myteask = new ThreadPoolExecutor(2,5,1, TimeUnit.SECONDS,new LinkedBlockingQueue<>(5), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
//        Thread t1 = new Thread(new task(1));
//        Thread t2 = new Thread(new task(2));
//        Thread t3 = new Thread(new task(3));
//        Thread t4 = new Thread(new task(4));
//        Thread t5 = new Thread(new task(5));
//
//
//
//        myteask.execute(t1);
//        myteask.execute(t2);
//        myteask.execute(t3);
//        myteask.execute(t4);
//        myteask.execute(t5);
//        myteask.execute(t5);

        ExecutorService mytesk = new ThreadPoolExecutor(2,4,1,TimeUnit.SECONDS,new LinkedBlockingQueue<>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        Future f1 = mytesk.submit(new task_futer(1));
        Future f2 = mytesk.submit(new task_futer(2));
        Future f3 = mytesk.submit(new task_futer(3));
        Future f4 = mytesk.submit(new task_futer(4));
        Future f5 = mytesk.submit(new task_futer(5));


        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());
        System.out.println(f4.get());
        System.out.println(f5.get());
    }






}
