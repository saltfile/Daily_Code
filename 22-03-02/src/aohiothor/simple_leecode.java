package aohiothor;

import java.util.*;

/**
 * 罗马数字转整数
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *输入: s = "III"
 * 输出: 3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


class RomanToInt{


    public int romanToInt(String s) {
        int res = 0;
        int preNum = getCom(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getCom(s.charAt(i));
            if (preNum < num) {
                res -= preNum;
            } else {
                res += preNum;
            }
            preNum = num;
        }
        res += preNum;
        return res;
    }



    public int getCom(char Roman){
        switch (Roman){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:return 0;
        }
    }




}


class MergeTwoLists{
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
       ListNode root = new ListNode(0);
       ListNode p = root;


        while (list1 != null&&list2 != null){
            if (list1.val < list2.val){
                p.next = list1;
                p = p.next;
                list1 = list1.next;
            }else {
                p.next = list2;
                p = p.next;
                list2 = list2.next;
            }
        }
        if (list1 == null){
            p.next = list2;
        }else {
            p.next = list1;
        }
        return root.next;
    }
}

class LengthOfLastWord{
    public int lengthOfLastWord(String s) {
        int flag = 0;
        int lens = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ' '&& flag != 0){
                return lens;
            }
            if (c != ' '&&flag == 0){
                flag = 1;
                lens++;
            }
            if (c != ' '&&flag == 1){
                lens++;
            }
        }
        return lens;
    }
}

class HasCycle{
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null){
            if (set.contains(head)){
                return true;
            }else {
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }
}


/**
 * 14. 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 */

class LongestCommonPrefix{
    public String longestCommonPrefix(String[] strs) {

        String s=strs[0];
        for (String string : strs) {
            while(!string.startsWith(s)){
                if(s.length()==0)return "";
                //公共前缀不匹配就让它变短！
                s=s.substring(0,s.length()-1);
            }
        }

    return s;
    }
}



class ArrMerge{
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        ArrayList<Object> objects = new ArrayList<>();


        ArrayList<Integer> res = new ArrayList();

        for (int i = 0; i < m; i++) {
            res.add(nums1[i]);
        }
        for (int i = 0; i < n; i++) {
            res.add(nums2[i]);
        }

        Collections.sort(res);
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = res.get(i);
        }


    }
}

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 */

class NumIslands{



    public void dfs(char[][] grid,int y,int x){
        //确定上界和下界
        int up = grid.length;
        int right = grid[0].length;
        if (grid[y][x] == '0')return;
        grid[y][x] = '0';
        if (y+1 < up && grid[y+1][x] == '1')dfs(grid, y+1, x);
        if (y-1 >= 0 && grid[y-1][x] == '1')dfs(grid, y-1, x);
        if (x+1 < right && grid[y][x+1] == '1')dfs(grid, y, x+1);
        if (x-1 >= 0 && grid[y][x-1] == '1')dfs(grid, y, x-1);
    }

    public int numIslands(char[][] grid) {
        int res = 0;
        int x = grid[0].length;
        int y = grid.length;

        //行遍历
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (grid[i][j] == '1'){
                    res++;
                    dfs(grid,i,j);
                }
            }
        }


        return res;
    }



}










public class simple_leecode {
    public static void main(String[] args) {

//        Collection<String> c = new ArrayList<String>();
//
//        TreeMap<String,String> tree = new TreeMap<>();


char [][] grid = {
        {'1','1','0','0','0'},
        {'1','1','0','0','0'},
        {'0','0','1','0','0'},
        {'0','0','0','1','1'}
};
//        int y = grid.length;
//        int x = grid[0].length;
//        grid[1][0] = '2';
//        grid[0][1] = '4';
//        System.out.println(grid);

        System.out.println(new NumIslands().numIslands(grid));


//        String[] srs = new String[]{"aa","ab"};
//        System.out.println(new LongestCommonPrefix().longestCommonPrefix(srs));
    }
}
