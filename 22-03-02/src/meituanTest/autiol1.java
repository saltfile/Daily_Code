package meituanTest;

import java.util.Random;
import java.util.Scanner;

public class autiol1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random ran = new Random();
        Integer len = in.nextInt();
        Integer[] arr = new Integer[len];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length-1; i++) {
            int num = ran.nextInt(len-1);
            while (num == 0){
                num = ran.nextInt(len-1);
            }
            arr[i] = num;

        }
        arr[len - 1] = len - 1;
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i]+" ");
        }


    }
}
