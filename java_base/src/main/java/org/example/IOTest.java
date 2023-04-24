package org.example;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


class File_con implements Callable<String>{
    public String path;
    public File_con(String path){
        this.path = path;
    }

    @Override
    public String call() throws Exception {
        FileReader input = new FileReader(path);
        char[] buf = new char[1024];
        StringBuilder res = new StringBuilder();
        int str_len = -1;
        while ((str_len = input.read(buf)) != -1){
            res.append(new String(buf,0,str_len));
        }

        return new String(res);
    }
}
class FileOut implements Runnable{
    public String filename;
    public String context;
    public FileOut(String filename,String context){
        this.filename = filename;
        this.context = context;
    }



    @Override
    public void run() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename,true);

            writer.write(context);
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}



public class IOTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask fileTask = new FutureTask(new File_con("/opt/git_Pro/Daily_Code/java_base/src/main/java/org/example/App.java"));
        Thread t1 = new Thread(fileTask);
        t1.start();
        System.out.println(fileTask.get());
        Thread t2 = new Thread(new FileOut("/opt/git_Pro/Daily_Code/java_base/src/main/java/org/example/xxx.txt","aaa"));
        t2.start();

    }
}
