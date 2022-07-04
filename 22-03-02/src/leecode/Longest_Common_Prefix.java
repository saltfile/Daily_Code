package leecode;

/**
 * 编写一个函数来查找字符串数组中最长的公共前缀字符串。
 *
 * 如果没有公共前缀，则返回一个空字符串""。
 */
class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int min = 0;
        int minlen = strs[0].length();
        for(int i = 1;i < strs.length;i++){
            if (minlen >strs[i].length() ){
                min = i;
                minlen = strs[i].length();
            }
        }
        for(int i = 0;i < minlen;i++){
            char c = strs[min].charAt(i);
            for(int j = 0;j < strs.length;j++){
                if(j==min)continue;
                if (strs[j].charAt(i)!=c){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[min];
    }
}







public class Longest_Common_Prefix {
    public static void main(String[] args) {
       String[] strings = {"flower","flow","flight"};
        System.out.println(new Solution14().longestCommonPrefix(strings));
    }
}
