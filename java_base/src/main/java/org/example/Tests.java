package org.example;

import java.util.*;

public class Tests {
    public static void main(String[] args) {
       List<Integer> ar = new ArrayList<>();
       ar.add(1);
       ar.add(2);
       ar.add(3);
        Collections.reverse(ar);
        System.out.println(ar);
    }
    public int xxx;
    public void fun(){
        System.out.println("我是Tests");
    }
}
