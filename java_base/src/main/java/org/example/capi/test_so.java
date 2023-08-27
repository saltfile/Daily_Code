package org.example.capi;

public class test_so extends Myfunc{
    static{
        System.load("/opt/git_Pro/Daily_Code/java_base/src/main/java/org/example/capi/libmy_test.so");
    }

    public static void main(String[] args) {



        test_so c = new test_so();
        int a = c.add(4,7);
        System.out.println(a);
    }


}
