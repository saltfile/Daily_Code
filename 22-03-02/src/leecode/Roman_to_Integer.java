package leecode;

import java.util.HashMap;
import java.util.Map;

class Solution13 {
    static Map<Character,Integer> map = new HashMap<>();
    static {
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
    }
    public int romanToInt(String s) {
        int res = 0;
        int sum =0;
        int prev = 0;
       for(int i =s.length() - 1;i >=0 ;i--){
           sum = map.get(s.charAt(i));
           if (sum < prev) // IX
               res -= sum;
           else
               res += sum;

           prev = sum;
       }
       return res;
    }
}


public class Roman_to_Integer {
    public static void main(String[] args) {
        System.out.println(new Solution13().romanToInt("MCMXCIV"));
    }
}
