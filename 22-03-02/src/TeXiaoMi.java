import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 12 4
 * Z G B A
 * ZQWEGRTBYAAI
 */

/**
 * ZZZGGGBBBAAA
 */

public class TeXiaoMi {
    public static void main(String[] args) {
     
        Scanner inpt = new Scanner(System.in);

        String fir =  "12 4";
        char[] tews = {'Z','G','B','A'};
        String thy =  "AZQWEGRTBYAAI";
        System.out.println(myFun(tews,thy));;

        String result = "";

    }
    public static String myFun(char[] words,String buf){

        StringBuilder result = new StringBuilder();
        char[] bufs = buf.toCharArray();
        int slow = 0;
        int fast = 0;

        int oldflag = 0;
        int flagIdx = 0;

        while (fast < bufs.length-1){

            if (oldflag == words.length-1){
                result.append(words[oldflag]);
                fast++;
                continue;
            }

            if (bufs[fast] != words[flagIdx]){
                fast++;
            }else {

                for (int i = slow; i < fast; i++) {
                    result.append(words[oldflag]);
                }
                slow = fast;
                fast++;
                if (flagIdx < words.length-1){
                oldflag = flagIdx;
                flagIdx++;
                }
            }
        }
        return new String(result);
    }
}
