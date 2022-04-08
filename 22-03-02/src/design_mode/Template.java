package design_mode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author saltfish
 * @date 2022-04-08
 * TODO:设计模式------->模板模式
 * 还是老样子拿着日志举例子，日志模板运行
 */

abstract class Temp{
    public void Execution(String str){
        System.out.print("====>");
        TempFunction(str);
    }
    abstract public void TempFunction(String str);
}

class log_Err extends Temp{

    @Override
    public void TempFunction(String str) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateLog = formatter.format(date);
        String log_type = "LOG ERROR";
        System.out.print("\033[1;" + 31 + "m" + log_type + "\033[0m \t");
        System.out.print("\033[1;" + 36 + "m" + dateLog + "\033[0m :");
        System.out.print("\033[1;" + 34 + "m" + "["+str+"]" + "\033[0m \t");
        System.out.println();
    }
}

class log_Warng extends Temp{
    @Override
    public void TempFunction(String str) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateLog = formatter.format(date);
        String log_type = "LOG WARNING";
        System.out.print("\033[1;" + 33 + "m" + log_type + "\033[0m \t");
        System.out.print("\033[1;" + 36 + "m" + dateLog + "\033[0m :");
        System.out.print("\033[1;" + 34 + "m" + "["+str+"]" + "\033[0m \t");
        System.out.println();
    }
}


public class Template {
    public static void main(String[] args) {
       new log_Err().Execution("我是报错");
       new log_Warng().Execution("我是警告");

    }

}
