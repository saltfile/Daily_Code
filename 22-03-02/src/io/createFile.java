package io;

import java.io.*;

public class createFile {
    public static void main(String[] args) throws IOException {
        File file = new File("/opt/22-03-02/src/io/sql.csv");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
        for(int i = 0;i < 10000;i++) {
            String str = "insert into labs values("+i+");";
            bw.write(str+"\n");
            bw.flush();
        }

        bw.close();


    }




}
