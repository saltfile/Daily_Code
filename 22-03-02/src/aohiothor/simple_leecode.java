package aohiothor;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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

/**
 * 11. 盛最多水的容器
 *
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 */

class MaxArea{

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;

        int res = 0;


        while (left < right){
            int area = Math.min(height[left],height[right])*(right-left);
            res = Math.max(area,res);

            if (height[left] > height[right])right--;
            else left++;
        }
        return res;

    }

}


/**
 * 796. 旋转字符串
 *
 * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 *
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
 *
 *     例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 */


class RotateString{
    public boolean rotateString(String s, String goal) {

        for (int i = 0; i < s.length(); i++) {
            StringBuilder str = new StringBuilder();
            char c = s.charAt(0);
            str.append(s.substring(1,s.length()));
            str.append(c);

            if (str.toString().equals(goal)){
                return true;
            }
            s = str.toString();
        }
        return false;

    }
}



class IsPalindrome{
    public boolean isPalindrome(ListNode head) {
     ListNode p = head;
     ArrayList<Integer> arr = new ArrayList<>();
     if (head.next == null)return true;

     while (p!=null){
         arr.add(p.val);
         p = p.next;
     }

     int left = 0;
     int right = arr.size()-1;

     while (left < right){
         if (arr.get(left) != arr.get(right)){
             return false;
         }
         left++;
         right--;

     }


return true;


    }
}




public class simple_leecode {
    public static void main(String[] args) {

        System.out.println(new RotateString().rotateString("abcde","cdeab"));


//        Collection<String> c = new ArrayList<String>();
//
//        TreeMap<String,String> tree = new TreeMap<>();


//char[][] grid = {
//        {'1','2','0','0','0'},
//        {'1','1','0','0','0'},
//        {'3','0','1','0','0'},
//        {'0','0','0','1','1'}
//};
//        System.out.println(grid[2][0]);
//
//int[][] ff = new int[10][10];
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                ff[i][j] = i*j;
//            }
//        }
//        System.out.println(ff[2][1]);


//        int y = grid.length;
//        int x = grid[0].length;
//        grid[1][0] = '2';
//        grid[0][1] = '4';
//        System.out.println(grid);

//        System.out.println(new NumIslands().numIslands(grid));


//        String[] srs = new String[]{"aa","ab"};
//        System.out.println(new LongestCommonPrefix().longestCommonPrefix(srs));
    }
}
