package aohiothor;

import java.util.HashSet;

/**
 * 罗马数字转整数
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *输入: s = "III"
 * 输出: 3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


class RomanToInt{


    public int romanToInt(String s) {
        int res = 0;
        int preNum = getCom(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getCom(s.charAt(i));
            if (preNum < num) {
                res -= preNum;
            } else {
                res += preNum;
            }
            preNum = num;
        }
        res += preNum;
        return res;
    }



    public int getCom(char Roman){
        switch (Roman){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:return 0;
        }
    }




}


class MergeTwoLists{
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
       ListNode root = new ListNode(0);
       ListNode p = root;


        while (list1 != null&&list2 != null){
            if (list1.val < list2.val){
                p.next = list1;
                p = p.next;
                list1 = list1.next;
            }else {
                p.next = list2;
                p = p.next;
                list2 = list2.next;
            }
        }
        if (list1 == null){
            p.next = list2;
        }else {
            p.next = list1;
        }
        return root.next;
    }
}

class LengthOfLastWord{
    public int lengthOfLastWord(String s) {
        int flag = 0;
        int lens = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ' '&& flag != 0){
                return lens;
            }
            if (c != ' '&&flag == 0){
                flag = 1;
                lens++;
            }
            if (c != ' '&&flag == 1){
                lens++;
            }
        }
        return lens;
    }
}

class HasCycle{
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null){
            if (set.contains(head)){
                return true;
            }else {
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }
}


public class simple_leecode {
}
