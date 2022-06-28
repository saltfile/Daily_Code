package leecode;


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
