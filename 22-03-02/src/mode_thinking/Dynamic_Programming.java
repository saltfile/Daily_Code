package mode_thinking;


import java.util.HashMap;
import java.util.Random;

/**
 * 动态规划
 * 详细理解：只是一个基础的思想这里只是简单的题以及题解
 *
 * 给定一个数组[1,5,2,4,6,8]并计算它的最长连续子序列
 * 例：1，2，4，6，8就是一条序列只要是递增的序列即可
 *
 *
 *
 *
 */
public class Dynamic_Programming {
    //这里的map就是为了不让动态规划走重复的路，防止增加复杂度
    //所以动态规划又称作带这个备忘录的规划 使用空间换时间的方式来更好的完成任务
    static HashMap<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) {
        int[] arr = new int[142];
        Random r = new Random();
        for (int i = 0; i < 142; i++) {
            arr[i] = r.nextInt()%100;
        }
        int res = 1;
        long startTime = System.nanoTime();
        for (int i = 0; i < arr.length; i++) {
            int s = Plib(arr,i);
            res =  res > s?res:s;
        }
        long overTime = System.nanoTime();
        System.out.println(res+"运行时间:"+(overTime-startTime));
    }
    public static int Plib(int[] arr,int num){
        if (map.get(num) != null)return map.get(num);
        //边界
        if (num == arr.length-1)return 1;
        int level = 1;
        for (int i = num+1; i < arr.length; i++) {
            if (arr[i]>arr[num]){
                int max_ = Plib(arr,i)+1;
                level = level > max_?level:max_;
            }
        }
        map.put(level,level);
        return level;
    }






}
