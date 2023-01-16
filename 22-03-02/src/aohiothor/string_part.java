package aohiothor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    public String reverseWords(String s) {


        char[] sr = remove_angtun(s.toCharArray());

        resvaes(sr,0,sr.length-1);
        resvaes_word(sr);

        String res = new String(sr);
        return res;
    }
    // 去掉多余的空格
    char[] remove_angtun(char[] arr){
        int left = 0;
        int right = arr.length-1;
        StringBuilder str = new StringBuilder();
        //先去掉前后的空格
        while (arr[left] == ' ')left++;
        while (arr[right] == ' ')right--;
        while (left <= right){
            char c = arr[left];
            if (c != ' ' || str.charAt(str.length()-1) != ' ') {
                str.append(c);
            }
            left++;
        }
        return new String(str).toCharArray();
    }

    void resvaes(char[] s,int l,int r){
        int left = l;
        int right = r;
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


    void resvaes_word(char[] s){
        //双指针
        int left = 0;
        int right = 1;
        int arr_len = s.length;
        while (left < s.length){
            while (right < arr_len && s[right] != ' ') {
                right++;
            }
            resvaes(s,left,right-1);
            //越过那个空格 在进行下一次循环
            left = right+1;
            right = left+1;
        }
        
        
        
        
    }

    public String reverseWordsKu(String s) {
        String[] str = s.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = str.length-1; i >= 0 ; i--) {
            String temp = str[i];
            if (temp.length()!=0){
                res.append(temp);
                res.append(" ");
            }
        }
        res.deleteCharAt(res.length()-1);
        return new String(res);

    }

}


/**
 * 剑指 Offer 58 - II. 左旋转字符串
 *
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 */


class ReverseLeftWords{
    public String reverseLeftWords(String s, int n) {
        int idx = n;
        char[] cs = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = n; i < cs.length; i++) {
            builder.append(cs[i]);
        }
        for (int i = 0; i < n; i++) {
            builder.append(cs[i]);
        }
        return new String(builder);

    }
}


/**
 * 28. 找出字符串中第一个匹配项的下标
 *
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果needle 不是 haystack 的一部分，则返回  -1 。
 *
 *
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 *
 * 典型的kmp算法或者滑动窗口算法
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


class StrStr{
    public int strStr(String haystack, String needle) {
        //KMP算法

        //初始化前缀表
        int[] pre = new int[needle.length()];

        getPrefix(pre,needle);
        int slow = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (slow > 0 && needle.charAt(slow) != haystack.charAt(i))
                slow = pre[slow-1];

            if(haystack.charAt(i) == needle.charAt(slow))
                    slow++;
            if (slow == needle.length())
                    return i - slow + 1;
        }

        return -1;
    }
    void getPrefix(int[] pre,String need){
        //起始位置
        int slow = 0;
        pre[0]  = 0;
        //测试整个子串
        for (int fast = 1; fast < need.length(); fast++) {
            //如果不相等就向后会退
            while (slow > 0&& need.charAt(fast) != need.charAt(slow))
                slow = pre[slow-1];
                //直到相等为止
            if(need.charAt(fast) == need.charAt(slow))
                    slow++;
                //然后讲前缀表补上
            pre[fast] = slow;
        }

    }

    //滑动窗口-库函数版


    public int strStr2(String haystack, String needle) {
        int left = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if ((i-left) == needle.length()){
                String a = haystack.substring(left+1,i+1);
                if (a.equals(needle)){
                    return left+1;
                }else {
                    left++;
                }
            }
        }
        return -1;
    }

}

/**
 * 459. 重复的子字符串
 *
 * 给定一个非空的字符串s，检查是否可以通过由它的一个子串重复多次构成。
 * 判断 s + s 拼接的字符串里是否出现一个s的的时候，要刨除 s + s 的首字符和尾字符，
 * 这样避免在s+s中搜索出原来的s，我们要搜索的是中间拼接出来的s。
 */



class RepeatedSubstringPattern{
    public boolean repeatedSubstringPattern(String s) {

        int len = s.length();

        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len + 1];


        // 构造 next 数组过程，j从0开始(空格)，i从2开始
        for (int i = 2, j = 0; i <= len; i++) {
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while (j > 0 && chars[i] != chars[j + 1]) j = next[j];
            // 匹配成功，j往后移
            if (chars[i] == chars[j + 1]) j++;
            // 更新 next 数组的值
            next[i] = j;
        }

        // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        if (next[len] > 0 && len % (len - next[len]) == 0) {
            return true;
        }
        return false;

    }
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

//        System.out.println(new ReverseWords().reverseWords(" a good   example "));
//        System.out.println(new ReverseLeftWords().reverseLeftWords("abcdefg",2));
//        System.out.println(new StrStr().strStr2("aabaaabaaac","aabaaac"));


        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("abab"));

    }
}
