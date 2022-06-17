package leecode;


import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> CharMap = new HashMap();
        int len = s.length();
        int sub = 0;
        int charS = 0;
        for (int j = 0; j < len; j++) {
            if (CharMap.containsKey(s.charAt(j))) {
                sub = Math.max(CharMap.get(s.charAt(j)), sub);
            }
            charS = Math.max(charS, j - sub + 1);
            CharMap.put(s.charAt(j), j + 1);
        }

        for(Character k:CharMap.keySet()){
            System.out.println("key:"+k+"   "+"value"+CharMap.get(k));
        }

        return charS;
    }
}



public class LongestString {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("asdsasssssasdasdsdfsdfgd"));
    }




}
