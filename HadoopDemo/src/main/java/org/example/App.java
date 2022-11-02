package org.example;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, URISyntaxException {
        HdfsClient hs = new HdfsClient();
//        hs.testmkdir();
        hs.filestatus();

    }
}
