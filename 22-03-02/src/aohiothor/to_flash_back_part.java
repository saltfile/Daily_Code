package aohiothor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 77. 组合
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 */

class Combine{



    public List<List<Integer>> combine(int n, int k) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        back_veol(n,1,k,path,res);
        return res;
    }

    public void back_veol(int n,int startidx,int k,List<Integer> path, List<List<Integer>> res){
        if (path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startidx; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            back_veol(n,i+1,k,path,res);
            path.remove(path.size()-1);//回溯步骤
        }

    }

}


/**
 * 216. 组合总和 III
 *
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 *
 *     只使用数字1到9
 *     每个数字 最多使用一次
 *
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 */

class CombinationSum3{
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        back(k,n,1,0,path,res);
        return res;
    }

    public void back(int k,int n,int startidx,int num,List< Integer> path,List<List<Integer>> res){
        if (path.size() == k&&num == n){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startidx; i <= 9; i++) {
            num+=i;
            path.add(i);
            back(k, n, i+1, num, path, res);
            num-=i;
            path.remove(path.size()-1);
        }
    }


}

/**
 * 17. 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */

class LetterCombinations{
    String[] str_map =new String[]{"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {

        List<String> res = new ArrayList<>();
        if (digits.length() == 0){
            return res;
        }
        StringBuilder path = new StringBuilder();
        back(digits,0,path,res);
        return res;
    }
    public void back(String digits,int startidx,StringBuilder path,List<String> res){
        if (path.length() == digits.length()){
            res.add(new String(path));
            return;
        }
        //先确定是哪一个按键
        int an =  Integer.parseInt(""+digits.charAt(startidx));

        String mas = str_map[an];


        for (int i = 0; i < mas.length(); i++) {
            path.append(mas.charAt(i));
            back(digits, startidx+1, path, res);
            path.deleteCharAt(path.length()-1);//回溯
        }
    }

}






public class to_flash_back_part {
    public static void main(String[] args) {
//        System.out.println(new Combine().combine(4,2));
//        System.out.println(new CombinationSum3().combinationSum3(3,7));
        System.out.println(new LetterCombinations().letterCombinations("23"));
    }

}
