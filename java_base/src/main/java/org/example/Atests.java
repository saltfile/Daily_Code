package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Atests {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("aaa");



        Object key = new Object();

        int muns = 0b1;
        System.out.println(muns);
        System.out.println(muns<<4);
        int h;
        int i = key.hashCode() >>> 16;
        int s =  key.hashCode();

        System.out.println(s^i);

        h = (key == null) ? 0 : (h = key.hashCode())  ^ (h>>>16);

        System.out.println(h);




        HashMap hashMap = new HashMap();
        hashMap.put("aaa","aaa");



    }
}
