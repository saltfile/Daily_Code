package aohiothor;


import sun.security.krb5.internal.PAData;

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


/**
 * 39. 组合总和
 *
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */

class CombinationSum{

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0)return res;

        Arrays.sort(candidates);


        List<Integer> path  = new ArrayList<>();

        back(candidates,0,target,0,path,res);
        return res;
    }



    public void back(int[] cond,int start,int target,int num,List<Integer> path,List<List<Integer>> res){
        if (num == target){
            res.add(new ArrayList<>(path));
            return;
        }
        if (num > target){
            return;
        }

        for (int i = start; i < cond.length; i++) {

            path.add(cond[i]);
            back(cond, i, target, num+cond[i], path, res);
            path.remove(path.size()-1);

        }


    }



}


/**
 * 40. 组合总和 II
 *
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。
 */


class CombinationSum2{
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res= new ArrayList<>();
        if (candidates.length == 0)return res;
        Arrays.sort(candidates);
        List<Integer> path = new ArrayList<>();
        back(candidates,0,target,0,path,res);

        return res;

    }


    public void back(int[] cond,int start,int target,int num,List<Integer> path,List<List<Integer>> res){
        if (num == target)
        {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < cond.length; i++) {
            if (i > start&&cond[i] == cond[i-1])continue;//减掉这个重复的支
            path.add(cond[i]);
            back(cond,i+1,target,num+cond[i],path,res);
            path.remove(path.size()-1);
        }

    }


}

/**
 * 131. 分割回文串
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 */

class Partition{
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s.length() == 0)return res;
        List<String> path = new ArrayList<>();
        back(s,0,path,res);
        return res;
    }

    public void back(String s,int start,List<String> path,List<List<String>> res){
        if (start >= s.length()){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            //判断是不是回文串
            if (isPar(s,start,i)){
                String spart = s.substring(start,i+1);
                path.add(spart);
            }else {
                continue;
            }
            back(s,i+1,path,res);
            path.remove(path.size()-1);
        }




    }


    boolean isPar(String s,int start,int end){
        for (int i = start, j = end; i < j; i++,j--) {
            if (s.charAt(i)!=s.charAt(j))return false;
        }
        return true;
    }
}


/**
 * 93. 复原 IP 地址
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 *     例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 */

class RestoreIpAddresses{
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length()<4||s.length()>12)return res;

        back(s,0,0,res);
        return res;

    }

    public void back(String s,int start,int point,List<String> res){
        if (point == 3){
            if (isIp(s,start,s.length()-1)){
                res.add(s);
            }
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isIp(s, start, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                point++;
                back(s,i+2,point,res);
                point--;
                s = s.substring(0, i + 1) + s.substring(i + 2);
            }else {
                break;
            }
        }
    }
    public boolean isIp(String s,int start,int end){
        if (start > end) {
            return false;
        }
        // 0开头的数字不合法
        if (s.charAt(start) == '0'&& start != end)return false;

        int num = 0;
        // 非数字字符不合法并且不能大于0~9
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9'||s.charAt(i) < '0'){
                return false;
            }
            num = num*10+(s.charAt(i)-'0');
            if (num > 255)return false;
        }
        return true;



    }


}


/**
 * 78. 子集
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
class Subsets{
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0)return res;
        List<Integer> path = new ArrayList<>();
        back(nums,0,path,res);
        return res;
    }


    public void back(int[] num,int start,List<Integer> path,List<List<Integer>> res){
        res.add(new ArrayList<>(path));
        if (start >= num.length) {
            return;
        }
        for (int i = start; i < num.length; i++) {
            path.add(num[i]);
            back(num,i+1,path,res);
            path.remove(path.size()-1);
        }

    }
}

/**
 * 90. 子集 II
 *
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */


class SubsetsWithDup{
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0)return res;
        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>();
        back(nums,0,path,res);
        return res;
    }


    public void back(int[] num,int start,List<Integer> path,List<List<Integer>> res){
        res.add(new ArrayList<>(path));
        if (start >= num.length) {
            return;
        }
        for (int i = start; i < num.length; i++) {
            if (i > start&&num[i] == num[i-1])continue;
            path.add(num[i]);
            back(num,i+1,path,res);
            path.remove(path.size()-1);
        }

    }
}



























/**
 * 491. 递增子序列
 *
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。你可以按 任意顺序 返回答案。
 *
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 */
class FindSubsequences{
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0)return res;
        List<Integer> path = new ArrayList<>();
        back(nums,0,path,res);
        return res;

    }

    public void back(int[] nums,int start,List<Integer> path,List<List<Integer>> res){
        //边界
        if (path.size() > 1){
            res.add(new ArrayList<>(path));
        }
        //目标数，出现次数
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = start; i < nums.length; i++) {
            //如果nums[i]小于上一层的数字则不添加进去
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1)) {
                continue;
            }
            //去重
            if (map.getOrDefault(nums[i], 0) >= 1) {
                continue;
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            path.add(nums[i]);
            back(nums, i + 1, path, res);
            path.remove(path.size() - 1);

        }
    }

}


/**
 * 46. 全排列
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。
 * 你可以 按任意顺序 返回答案。
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
class Permute{
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0)return res;
        List<Integer> path = new ArrayList<>();
        boolean[] use = new boolean[nums.length];
        back(nums,use,path,res);
        return res;
    }

    public void back(int[] nums,boolean[] use,List<Integer> path,List<List<Integer>> res){
        if (nums.length == path.size()){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (use[i])continue;
            use[i] = true;
            path.add(nums[i]);
            back(nums, use, path, res);
            use[i] = false;
            path.remove(path.size()-1);
        }
    }


}

/**
 * 47. 全排列 II
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */


class PermuteUnique{
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0)return res;
        List<Integer> path = new ArrayList<>();
        boolean[] use = new boolean[nums.length];
        Arrays.sort(nums);
        back(nums,use,path,res);
        return res;
    }
    public void back(int[] nums,boolean[] use,List<Integer> path,List<List<Integer>> res){
        if (nums.length == path.size()){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //去重
            if (i > 0&&nums[i]==nums[i-1]&&use[i-1])continue;

            if (!use[i]){
            use[i] = true;
            path.add(nums[i]);
            back(nums, use, path, res);
            use[i] = false;
            path.remove(path.size()-1);
            }
        }
    }
}


/**
 * 51. N 皇后
 *
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 */

class SolveNQueens{
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if (n == 0)return res;
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        back(n, 0, chessboard,res);
        return res;

    }

    public void back(int n,int row,char[][]chessboard,List<List<String>>res){
        if (row == n) {
            res.add(Array2List(chessboard));
            return;
        }

        for (int col = 0;col < n; ++col) {
            if (IsPush(row, col,n, chessboard)) {
                chessboard[row][col] = 'Q';
                back(n, row+1, chessboard,res);
                chessboard[row][col] = '.';
            }
        }

    }
    public List Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }



    public boolean IsPush(int row, int col,int n, char[][] chessboard){
        for (int i=0; i<row; ++i) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        for (int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        for (int i=row-1, j=col+1; i>=0 && j<=n-1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;

    }



}

//物资数量n s
class Ens{
    public int Pk(int n,int s){
        ArrayList res = new ArrayList();
        back(n,s,res);
        return res.size();
    }


    public void back(int idx,int s,ArrayList res){
        if (s == 0&&idx == 0){
            res.add(1);
            return;
        }else if (idx == 0){
            return;
        }

        for (int i = 1; i <= s; i++) {
            s-=i;
            back(idx-1,s,res);
            s+=i;
        }
    }
}









public class to_flash_back_part {
    public static void main(String[] args) {
//        System.out.println(new Combine().combine(4,2));
//        System.out.println(new CombinationSum3().combinationSum3(3,7));
//        System.out.println(new LetterCombinations().letterCombinations("23"));

//        int[] nums = new int[]{1,1,2};
//        System.out.println(new PermuteUnique().permuteUnique(nums));

        System.out.println(new Ens().Pk(3,5));



//        System.out.println(new Permute().permute(nums));
//        System.out.println(new FindSubsequences().findSubsequences(nums));
//        System.out.println(new Subsets().subsets(nums));

//        System.out.println(new CombinationSum().combinationSum(nums,7));
//        System.out.println(new CombinationSum2().combinationSum2(nums,8));


//        System.out.println(new Partition().partition("aab"));
//        System.out.println(new RestoreIpAddresses().restoreIpAddresses("25525511135"));
    }

}
