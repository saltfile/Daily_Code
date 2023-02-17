package org.example;

import org.openjdk.jol.info.ClassLayout;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );


        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());



    }
}
