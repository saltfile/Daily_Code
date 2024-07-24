package meituanTest;

import java.util.Scanner;

public class autiol2 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        Integer q = in.nextInt();//输入次数
//        //每次代表一次询问
//        for (int i = 0; i < q; i++) {
//            Integer n = in.nextInt();//长度
//            Integer k = in.nextInt();//次数
//            String str = in.next();
//            String res = in.next();
//
//            if (getResult(str,res,k)){
//                System.out.println("Yes");
//            }else {
//                System.out.println("No");
//            }
//
//        }
        System.out.println(getResult("bbb", "aaa", 101));


    }




    public static Boolean getResult(String str,String res,Integer k){
        char[] ch = str.toCharArray();
        for (int i = 0; i < res.length(); i++) {
            char c = res.charAt(i);
            int num = 0;
            if (ch[i] < c) {
                num = c - ch[i] ;//相差多少
            } else if (ch[i] == c) {
                continue;
            } else {
                num = c - ch[i]+26;
                System.out.println(num);
            }
            k-=num;
        }
        System.out.println(k);
        if (k == 0){
            return true;
        }else {
            if (k > 0&&k%26 == 0){
                return true;
            }
            return false;
        }

    }

}
