package leecode;

/**
 * 给定一个整数x，true如果x是回文整数则返回。
 *
 * 当一个整数向后读和向前读一样时，它就是一个回文数。
 *
 * 例如，121是回文，而123不是。
 * 例:
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 */

class Solution9 {
    public boolean isPalindrome(int x) {
        String x2 = Integer.toString(x);
        String reverse = new StringBuffer(x2).reverse().toString();
        if(reverse.equals(x2)){
            return true;
        }else {
            return false;
        }
    }
}



public class PalindromeNumber {
    public static void main(String[] args) {
        System.out.println(new Solution9().isPalindrome(122));
    }
}
