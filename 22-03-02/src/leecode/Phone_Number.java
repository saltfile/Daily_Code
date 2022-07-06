package leecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个包含数字的字符串2-9，返回该数字可以表示的所有可能的字母组合。按任意顺序返回答案。
 *
 * 下面给出了数字到字母的映射（就像在电话按钮上一样）。请注意，1 不映射到任何字母。
 * 号码对应关系
 *   '2', "abc"
 *   '3', "def"
 *   '4', "ghi"
 *   '5', "jkl"
 *   '6', "mno"
 *   '7', "pqrs"
 *   '8', "tuv"
 *   '9', "wxyz"
 */
class Solution17 {
    private static Map<Character,String> map = new HashMap<>();
    static {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }
    List<String> res = null;
    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if(digits.length() == 0){
            return res;
        }
        BackTracking(0,digits,new StringBuilder());
        return res;
    }

    private void BackTracking(int lens,String digits,StringBuilder str){
        if (lens == digits.length()) {
            res.add(str.toString());
            return;
        }
        char key = digits.charAt(lens);
        for (char c:map.get(key).toCharArray()) {
            str.append(c);
            BackTracking(lens+1, digits, str); // 递归
            str.deleteCharAt(lens);
        }
    }
}
public class Phone_Number {
    public static void main(String[] args) {
        System.out.println(new Solution17().letterCombinations("23"));
    }
}
