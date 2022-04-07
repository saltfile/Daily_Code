package design_mode;

import javafx.concurrent.Task;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author SaltFish
 * @date 2022-04-04
 * TODO:设计模式------->单例模式
 * 对于单例模式，我试想的场景是应用中的日志管理
 * 因为这种日志业务只要打开就只能有一个单例去应用
 * 否则日志内容追加难以统一
 */


//单例的日志集合
class TaskLog{
    private static TaskLog taskLog = new TaskLog();

    private ArrayList<String> logs = new ArrayList<>();
    private TaskLog(){}
    //展示所有的日志消息
    public void ShowAllLog(){
        String str = "Log-INFO";
        for(int i = 0;i < this.logs.size();i++){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateLog = formatter.format(date);
        String log = this.logs.get(i);

        System.out.print("\033[1;" + 33 + "m" + str + "\033[0m \t");
        System.out.print("\033[1;" + 36 + "m" + dateLog + "\033[0m :");
        System.out.print("\033[1;" + 34 + "m" + "["+log+"]" + "\033[0m \t");
        System.out.println();
        }
    }
    //单条展示日志
    public void getShowLog(int num){
        String str = "Log-INFO";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateLog = formatter.format(date);
        String log = logs.get(num);
        System.out.print("\033[1;" + 33 + "m" + str + "\033[0m \t");
        System.out.print("\033[1;" + 36 + "m" + dateLog + "\033[0m :");
        System.out.print("\033[1;" + 34 + "m" + "["+log+"]" + "\033[0m \t");
        System.out.println();
    }
    //获取单条日志信息
    public String getLog(int num){
      return this.logs.get(num);
    }
    //输入新的日志
    public void setLog(String log){
        logs.add(log);
    }
    //获取目前日志长度
    public int getCount(){return this.logs.size();}
    //单例的构造
    public static TaskLog getLogs(){
        return taskLog;
    }
}


//TODO:线程模拟:添加日志
class LogAdder implements Runnable{
    private TaskLog taskLog = TaskLog.getLogs();
    private Scanner input = new Scanner(System.in);
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            //通过控制台输入
            String logstr = input.nextLine();
            taskLog.setLog(logstr);
        }
    }
}
//TODO:对单例进行的日志监控
class monitor implements Runnable{
    int port = 0;
    int newCount = 0;

    public monitor(int port){
        this.port = port;
    }
    @Override
    public void run() {
        Thread.currentThread().setName("[监控->"+this.port+"]");
        System.out.println(Thread.currentThread().getName()+"启动");
        TaskLog logs = TaskLog.getLogs();
        int count = 0;
        while (true) {
            //每两秒进行一次读取日志的操作
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(logs.getCount() > count) {
                for (int i = count; i < logs.getCount();i++)
                    logs.getShowLog(i);
                    count = logs.getCount();
            }else {
                continue;
            }
        }
    }
}

public class SingleCase {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new monitor(1));
        thread.start();
        Thread logadd = new Thread(new LogAdder());
        logadd.start();
    }
}
