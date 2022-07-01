package leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * . 最长回文子串
 * 中等的
 * 给定一个字符串s，返回中最长的回文子串。s
 *
 * 输入： s = "babad"
 * 输出： "bab"
 * 解释： "aba" 也是一个有效的答案。
 */
class Solution5 {
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = len1 > len2 ? len1:len2;
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
public class Longest_Common {
    public static void main(String[] args) {
        System.out.println(new Solution5().longestPalindrome("aslkdnkjafhkjsdhfksdhkjfsdkjfkjahsdlsa"));
    }
}
