package leecode;

/**
 * 给定一个带符号的 32 位整数x，返回x其数字反转。
 * 如果反转x导致值超出有符号的 32 位整数范围，则返回。[-231, 231 - 1]0
 */



class Solution7 {
    public int reverse(int x) {
        try{
        String x2 = Integer.toString(x);
        if (x==0){
            return 0;
        }
        if(x >= 0) {
            String reverse = new StringBuffer(x2).reverse().toString();
            return  Integer.parseInt(reverse);
        }else {
            x -= (2*x);
            x2 = Integer.toString(x);
            String reverse = new StringBuffer(x2).reverse().toString();
            return Integer.parseInt("-"+reverse);
        }
        }catch (NumberFormatException e){
            return 0;
        }

    }
    public int reverse2(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if(pop > 7&&(rev == Integer.MAX_VALUE / 10))return 0;
            if(pop < -8&&(rev == Integer.MIN_VALUE / 10))return 0;
            if(rev > Integer.MAX_VALUE/10)return 0;
            if (rev < Integer.MIN_VALUE/10)return 0;
            rev = rev * 10 + pop;

        }
        return rev;

    }
}
public class Reverse_Integer {
    public static void main(String[] args) {
        //9646324351
        System.out.println(new Solution7().reverse2(1534236469));
    }
}
