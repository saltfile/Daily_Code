package aohiothor;
// 散

//在一段字符串中找到最长的且不重复的字符串 都是小写字母

import java.util.ArrayList;
import java.util.List;

class soult1{
    public ArrayList<String> MaxBu(String str){
        int left = 0;
        int max = 0;
        ArrayList<String> arr = new ArrayList<>();
        for (int right = 1; right < str.length(); right++) {
            String st = str.substring(left,right);
            //最起码要大于前面的max
            if (st.length() > max){
                //如果是最长
                if (isMString(st)){
                    arr.clear();
                    arr.add(st);
                    max = st.length();
                }else {
                    left = right-1;
                }
            }else if(st.length() == max){
                if (isMString(st)){
                    arr.add(st);
                }
            }
        }
        return arr;

    }
    public boolean isMString(String s){
        int[] _tab = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i)-'a';
            if (_tab[idx] == 0){
                _tab[idx] = 1;
            }else {
                return false;
            }
        }
        return true;
    }
}


/**
 * 2. 两数相加
 *
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
class AddTwoNumbers{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode node = res;

        int car = 0;
        while (l1 != null || l2 != null||car != 0){
            int var1 = l1 != null?l1.val:0;
            int var2 = l2 != null?l2.val:0;

            int num = var1+var2+car;

            ListNode news = new ListNode(num%10);
            node.next = news;
            node = node.next;
            car = num/10;

            if (l1!=null)l1 = l1.next;
            if (l2 != null)l2 = l2.next;
        }
        return res.next;
    }



}

/**
 * 258. 各位相加
 *
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 */

class AddDigits{
    public int addDigits(int num) {
        while (num >= 10){
            num = number_sum(num);
        }
        return num;
    }


    public int number_sum(int num){
        int res = 0;
        while (num > 0){
            res += num%10;
            num/=10;
        }
        return res;
    }

}


public class cont_part {
    public static void main(String[] args) {
        System.out.println(new soult1().MaxBu("stringsabcvdb"));
    }
}
