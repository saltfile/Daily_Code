package org.example;




class MyBudlier{
    private String str = "";

    public MyBudlier append(String s){
        str+=s;
        return this;
    }
    public MyBudlier append(char c){
        str+=c;
        return this;
    }
    public String toString(){
        return this.str;
    }


    public MyBudlier clear(){
        str = "";
        return this;
    }
}








public class Gaojie {

    public static void main(String[] args) {

        MyBudlier sre = new MyBudlier();
        sre.append("aaa").append("bbb").append("---").append('a').clear();
        System.out.println(sre.toString());

    }


}
