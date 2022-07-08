package leecode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定n一对括号，编写一个函数来生成格式良好的括号的所有组合。
 * 输入： n = 3
 * 输出： ["((()))","((()())","(())()","()(())","()()( )"]
 *
 *输入： n = 1
 * 输出： ["()"]
 *
 * 依然采用回溯法
 * void backtracking(参数) {
 *     if (终止条件) {
 *         存放结果;
 *         return;
 *     }
 *
 *     for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
 *         处理节点;
 *         backtracking(参数); // 递归
 *         回溯，撤销处理结果
 *     }
 */

class Solution22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtracking(res,new StringBuilder(),0,0,n);
        return res;
    }

    /**
     * start   "("    end     ")"
     */
    private void backtracking(List<String> list, StringBuilder builder, int start, int end, int max) {
        if (builder.length() == max*2) {
            list.add(builder.toString());
            return;
        }
        if (start < max) {
            builder.append("(");
            backtracking(list, builder, start+1, end, max);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (start > end) {
            builder.append(")");
            backtracking(list, builder, start, end+1, max);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

}




public class GenerateParentheses {
    public static void main(String[] args) {
        List<String > res = new Solution22().generateParenthesis(3);
        for(String s:res){
            System.out.println(s);
        }
    }
}
