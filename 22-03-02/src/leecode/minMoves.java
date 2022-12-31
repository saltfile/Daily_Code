package leecode;

import java.util.Arrays;

/**
 * 一个房间里有 n个座位和 n名学生，房间用一个数轴表示。给你一个长度为 n 的数组 seats ，其中 seats[i] 是第 i 个座位的位置。同时给你一个长度为 n的数组students，其中students[j]是第 j位学生的位置。
 *
 * 你可以执行以下操作任意次：
 *
 *     增加或者减少第 i 位学生的位置，每次变化量为 1 （也就是将第 i 位学生从位置 x 移动到 x + 1 或者 x - 1）
 *
 * 请你返回使所有学生都有座位坐的 最少移动次数 ，并确保没有两位学生的座位相同。
 *
 * 请注意，初始时有可能有多个座位或者多位学生在 同一 位置。
 *
 *
 * 2022年的最后一道题 很简单
 */
public class minMoves {
    public static int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int res = 0;
        for (int i = 0;i < seats.length;i++){
            res+=Math.abs(seats[i]-students[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a =new int[]{3,1,2};
        int[] b = new int[]{7,5,6};
        System.out.println(minMovesToSeat(a,b));
    }
}
