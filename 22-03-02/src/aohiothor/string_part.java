package aohiothor;


import java.util.ArrayList;
import java.util.List;

/**
 * 344. 反转字符串
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 *
 * 双指针牛逼！！！！
 */


class ReverseString{
    public void reverseString(char[] s) {
        //定义双指针进行交换
        int left = 0;
        int right = s.length-1;
        while (right > left){
            //初级交换
            char temp ;
            temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            right--;
            left++;
        }
    }
}

/**
 * 541. 反转字符串 II
 *
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 *
 *     如果剩余字符少于 k 个，则将剩余字符全部反转。
 *     如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 *
 *     这里说人话就是每次＋2k然后第一个k做反转就行
 */

class ReverseStr{

    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i+=(2*k)) {
            //右边指针防止越界
            swap(arr,i,Math.min(arr.length-1,i+k-1));
        }
        return new String(arr);
    }

    public void swap(char[] arr,int l,int r){
        int left = l;
        int right = r;
        while (right > left){
            //初级交换
            char temp ;
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            right--;
            left++;
        }
    }
}

/**
 * 剑指 Offer 05. 替换空格
 *
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 */


class ReplaceSpace{

    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)==' '){
                res.append("%20");
            }else {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }

}


/**
 * 151. 反转字符串中的单词
 *
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 *
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 *
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 *
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格
 *
 * 示例 1：
 *
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 */


class ReverseWords{


}









public class string_part {
    public static void main(String[] args) {
//        String arr = "hello";
//        char[] a = arr.toCharArray();
//        new ReverseString().reverseString(a);
//
//        for (char c:a){
//            System.out.print(c+"   ");
//        }


//        System.out.println(new ReverseStr().reverseStr("abcdefg",8).toCharArray());



    }
}
