package leecode;

import java.util.HashSet;
import java.util.Set;

public class String_Diffrent {
    public static int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<String>();
        int  head = 0, p2;
        while (true) {
            while (head < word.length() && !Character.isDigit(word.charAt(head))) {
                head++;
            }
            if (head == word.length()) {
                break;
            }
            p2 = head;
            while (p2 < word.length() && Character.isDigit(word.charAt(p2))) {
                p2++;
            }
            //查看自己的数据是否是连续的且第一位不能是0
            while (p2 - head > 1 && word.charAt(head) == '0') {
                head++;
            }
            set.add(word.substring(head, p2));
            head = p2;
        }
        return set.size();
    }



    public static void main(String[] args) {
        System.out.println(numDifferentIntegers("a1b01c001"));
    }
}
