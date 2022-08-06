package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class Solution916 {
    private int[] MyToArray(String str){
        int[] res = new int[26];
        if (str == null)return res;
        for (char c: str.toCharArray())
            res[c - 'a']++;
        return res;
    }
    public List<String> wordSubsets(String[] words1, String[] words2) {
        ArrayList<String> ans = new ArrayList<>();
        int[] res = MyToArray(null);
        for (String str:words2){
            int[] temp = MyToArray(str);
            for(int i = 0;i < res.length;i++){
             res[i] = Math.max(res[i],temp[i]);
            }
        }

        for(String str:words1){
            int[] wtemp = MyToArray(str);
            int flag = 0;
            for (int i = 0;i < wtemp.length;i++)
                if (wtemp[i] < res[i]){flag =1; break;}
            if (flag!=1)
            ans.add(str);
        }

        return ans;

    }
}



public class ReducetoSingleWord {
    public static void main(String[] args) {
        String[] s1 = {"amazon","apple","facebook","google","leetcode"};
        String[] s2 = {"e","o"};

        System.out.println(new Solution916().wordSubsets(s1,s2));






    }


}
