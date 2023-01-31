package io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;





class IoMap{
    Map<String, FileOutputStream> outmap = new HashMap<>();


    public void saveOut(String path,FileOutputStream output){
        outmap.put(path,output);
    }


    public FileOutputStream getOut(String path){
        return outmap.get(path);
    }
}





public class IoTest {

    public static void main(String[] args) throws IOException {
        String path = "/opt/git_Pro/Daily_Code/22-03-02/src/io/aaa.txt";
        FileOutputStream out = new FileOutputStream(new File(path),true);
        IoMap map = new IoMap();
        map.saveOut("aaa",out);

        BufferedWriter wir = new BufferedWriter(new OutputStreamWriter(map.getOut("aaa")));
        wir.write(new String("aaaaab"));
        wir.flush();

    }
}
