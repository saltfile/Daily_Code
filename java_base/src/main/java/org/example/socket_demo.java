package org.example;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Servcer implements Runnable{

    ServerSocket Ssocket ;
    public Servcer(int port) throws IOException {
        this.Ssocket = new ServerSocket(port);
    }
    @Override
    public void run() {
        try {
            Socket socket = Ssocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String str;
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while ((str = reader.readLine())!=null){
                System.out.println(str);
                writer.write("好我知道了");
                writer.flush();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
class Clienst implements Runnable{
    private Socket cli ;
    public Clienst(String host,int port) throws IOException {
        this.cli = new Socket(host, port);
    }

    @Override
    public void run() {
        try {
           BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.cli.getOutputStream()));
            writer.write("从玉，我爱你");
            writer.flush();
            this.cli.shutdownOutput();
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.cli.getInputStream(),"UTF-8"));
            String str;
            while ((str = reader.readLine())!=null){
                System.out.println(str);
            }
            this.cli.shutdownInput();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}




public class socket_demo {
    public static void main(String[] args) throws IOException {
        Thread s1 = new Thread(new Servcer(8787));
       s1.start();
    }
}
