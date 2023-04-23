package aohiothor;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 904. 水果成篮
 *
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 *
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 *
 *     你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
 *     你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 *     一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
 *
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 */


class TotalFruit{
    private Map<Integer,Integer> map = new HashMap<>();




    public int totalFruit(int[] fruits) {
        int sum = 0;
        int start = 0;
        int res = 0;//加起来的数额
        for (int i = 0; i < fruits.length; i++) {
            //如果不空就加一
            map.put(fruits[i],map.getOrDefault(fruits[i],0)+1);


            while (map.size() > 2){

                map.put(fruits[start], map.get(fruits[start])-1);
                if (map.get(fruits[start])==0){
                    map.remove(fruits[start]);
                }
                start++;
            }
            res = Math.max(res, i - start + 1);
        }
        return res;
    }
}

/**
 * 35. 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 */
class  SearchInsert{


    public int searchInsert(int[] nums, int target) {

        int left = 0;
        int right = nums.length-1;
        int res = nums.length;

        while (left<=right){
            int mid = (left+right)/2;
            if (target > nums[mid]){
                left = mid + 1;
            }else{
                res = mid;
                right = mid-1;
            }
        }
        return res;
    }
}


/**
 * 59. 螺旋矩阵 II
 *
 * 给你一个正整数n ，生成一个包含 1 到n^2所有元素，且元素按顺时针顺序螺旋排列的n x n 正方形矩阵 matrix 。
 * n = 3
 *    1 2 3
 *    8 9 4
 *    7 6 5
 *
 *
 */

class GenerateMatrix{
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];

        int loop = 0;//对角线的轮回次数

        int x = 0,y = 0;//第一个对角线开始

        int num = 1;//用来赋值

        while (loop++ < n/2 ){
            int i = x;
            int j = y;
            //上侧从左到右
            for (j = x; j < n - loop; j++) {
                arr[x][j] = num++;
            }

            //右侧从上到下
            for (i = x; i < n - loop ; i++) {
                arr[i][j] = num++;
            }

            //下侧从右向左
            for (; j >= loop ;j--){
                arr[i][j] = num++;
            }
            //左侧从下向上
            for (;i >= loop;i--){
                arr[i][j] = num++;
            }
            x++;
            y++;

        }
        if (n%2 != 0){
            arr[n/2][n/2] = num;
        }
        return arr;
    }
}


/**
 * 04-23 腾讯云面试 算法：
 * 按照规定输出对应的算法
 *
 * 数组：
 *   1,2,3,4,
 *   5,6,7,8
 * 输出:
 *  1,2,3,4,8,7,6,5
 *
 *
 */
class Arolties{

    public ArrayList<Integer> arsd(ArrayList<ArrayList<Integer>> arr){
        ArrayList<Integer> res = new ArrayList<>();
        int flag = 0;//0正1反
        for (ArrayList<Integer> nums:arr) {
            if (flag == 0){
                for (int i = 0; i < nums.size(); i++) {
                    res.add(nums.get(i));
                }
                flag = 1;
            }else {
                for (int i = nums.size() - 1; i >= 0; i--) {
                    res.add(nums.get(i));
                }
                flag = 0;
            }

        }

        return res;


    }

}




















public class array_part {

    public static void main(String[] args) {

        System.out.println(new GenerateMatrix().generateMatrix(4));

    }




}
