package org.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int m=1;
        while(list.size()!=1){
            for (int i = 0; i < list.size(); i++) {
                if(m>3){
                    m=1;
                }
                if(m%3==0){
                    list.remove(i);
                    i--;
                }
                m++;
            }
        }
        System.out.println(list);
//        System.out.println( "Hello World!" );
//
//
//        Object o = new Object();
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());



    }
}
