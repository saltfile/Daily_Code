package leecode;

/**
 * 罗马数字由七个不同的符号表示 ：I、V、X、L、C和。DM
 *
 * 符号       值
 * 我 1
 * 在第 5
 * X 10
 * 大号 50
 * C 100
 * D 500
 * 米1000
 * 例如， 2写成II 罗马数字，只是两个加在一起。12写成 XII, 就是X + II. 数27写为XXVII，即XX + V + II。
 */
class Solution12 {
    public String intToRoman(int num) {
        int[] digits = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] values = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        String result = "";
        int i = 0;
        int arrlen = digits.length;
        while (i < arrlen){
            while (num>=digits[i]){
                num = num-digits[i];
                result+=values[i];
                if(num == 0) break;
            }
            if(num == 0)break;
            i++;
        }
        return result;
    }
}





public class Integer_to_Roman {
    public static void main(String[] args) {
        System.out.println(new Solution12().intToRoman(10000));
    }
}
