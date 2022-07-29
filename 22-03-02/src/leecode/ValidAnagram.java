package leecode;


import java.util.HashMap;

class Solution242 {
    public boolean isAnagram(String s, String t) {
        if (s.length()!=t.length())return false;
        //26字母对应
        int[] fs = new int[26];
        int[] ft = new int[26];
        //赋值
        for(char c: s.toCharArray()){
            fs[c-'a']++;
        }
        for(char c: t.toCharArray()){
            ft[c-'a']++;
        }
        //对比
        for(int i = 0; i < 26; i++){
            if(fs[i] != ft[i])
                return false;
        }
        //O(s.len+t.len+26)
        return true;
    }
}



public class ValidAnagram {
    public static void main(String[] args) {
        System.out.println(new Solution242().isAnagram("ab","a"));
    }
}
