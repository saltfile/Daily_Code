package org.example;

import java.io.IOException;

public class socket_uo {
    public static void main(String[] args) throws IOException {
        Thread c1 = new Thread(new Clienst("127.0.0.1",8787));
        c1.start();
    }
}
