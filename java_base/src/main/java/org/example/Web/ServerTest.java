package org.example.Web;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(10000, 5);
        int acceptCount = 0;
        while (true) {
            Socket client = server.accept();
            client.setReceiveBufferSize(4);
             InputStream stream =  client.getInputStream();
             byte[] bytes = new byte[100];
             int lens = 0;
             while ((lens = stream.read(bytes))>0){
                 System.out.println("本次拿到："+lens);

             }
            System.out.println("new connection has connected, num=" + acceptCount++);
            // 连接成功后，不处理该连接的任何请求
        }

    }
}
