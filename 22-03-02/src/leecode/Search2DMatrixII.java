package leecode;

import java.util.Arrays;

/**
 * target编写一个在m x n整数矩阵中搜索值的高效算法matrix。该矩阵具有以下性质：
 *
 * 每行中的整数从左到右升序排序。
 * 每列中的整数从上到下按升序排序。
 */


class Solution240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;//行
        int j = matrix[0].length-1;//列
        while (i  >= 0&&i < matrix.length&&j >= 0&&j < matrix[0].length){
            if (matrix[i][j] == target)return true;
            if (matrix[i][j] > target)
            {
                System.out.println(matrix[i][j]);
             j--;
            }else if (matrix[i][j] < target)
            {
                System.out.println(matrix[i][j]);
             i++;
            }
        }

        return false;
    }
}




public class Search2DMatrixII {
    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(new Solution240().searchMatrix(matrix,22));
    }
}
