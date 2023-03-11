package org.example.Web;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ClientTest {
    public static void main(String[] args) throws Exception {
        byte[] one_kb = new byte[10 * 1024];
        byte a = 'a';
        Arrays.fill(one_kb, a);

        Socket client = new Socket("127.0.0.1", 10000);
        OutputStream outputStream = client.getOutputStream();

        for (int i = 0; i < 5; i++) {
            try {
                outputStream.write(one_kb);
                outputStream.flush();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
