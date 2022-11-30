package org.example;

/**
 * 算法---第一天
 * KMP字符匹配算法
 *Rabin Karp 字符匹配算法
 *
 * @author saltfish
 */


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Rabin-Karp 算法
 * 这是一个很简单优雅的算法
 *滑动窗口算法延伸
 *例如在一个很长的字符串中查找相同的字符
 * 例：DNA 序列由四种碱基 A, G, C, T 组成，现在给你输入一个只包含 A, G, C, T 四种字符的字符串 s 代表一个 DNA 序列，请你在 s 中找出所有重复出现的长度为 10 的子字符串
 *
 * 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * 输出：["AAAAACCCCC","CCCCCAAAAA"]
 * 解释：子串 "AAAAACCCCC" 和 "CCCCCAAAAA" 都重复出现了两次。
 *
 * 这个算法相当于利用了一块可以滑动的长度为10的窗口一点点向后查看匹配
 *
 *
 *
 *
 */


class Rabin_Karp{
    /**
     *
     * @param s 需要分析的字符串->DNA序列
     * @return 返回重复的结果
     */
    List<String> soulorithm(String s){
        //重新做一个数组当做编码
        int[] arr = new int[s.length()];
        for (int i = 0; i < arr.length; i++) {
            switch (s.charAt(i)){
                case 'A':arr[i] = 0;break;
                case 'G':arr[i] = 1;break;
                case 'C':arr[i] = 2;break;
                case 'T':arr[i] = 3;break;
            }
        }

        HashSet<Integer> wins = new HashSet<>();
        HashSet<String> res = new HashSet<>();

        //十个大小的窗口
        int Longs = 10;
        //4个排列
        int RBase = 4;
        //用来删除最后一个高位字符的数值
        int LR =(int) Math.pow(RBase,Longs-1);

        //抽象出的hash值
        int winhash = 0;

        //操作下标
        int left = 0,right = 0;
        for (int i = 0; i < arr.length; i++) {
            // 首先获得当前hash值
            winhash = RBase*winhash+arr[right];
            right++;
            //  当窗口到10的时候
            if (right-left == Longs){
                //如果重复了
                if (wins.contains(winhash)){
                    res.add(s.substring(left,right));
                }else {
                    //不重复就放在窗口set里
                    wins.add(winhash);
                }
                //将窗口往前面移动，删除最高位的left 并向前移动
                winhash = winhash - arr[left]*LR;
                left++;
            }
        }

        return new LinkedList<>(res);
    }
}


/**
 *
 * KMP算法
 * KMP算法有点像是状态机
 * 通过有限状态机的方式将搜索进行匹配
 * 例： 找出字符串中第一个匹配项的下标
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class KMP_{
    public int strStr(String haystack, String needle) {
        int[][] stat = new int[needle.length()][256];
        //设置状态机开始状态为1为字符串初始状态
        stat[0][needle.charAt(0)] = 1;
        //用来回溯状态的
        int X = 0;

        for (int i = 0; i < needle.length(); i++) {
            for (int c = 0; c < 256; c++) {
                if (needle.charAt(i) == c)
                    stat[i][c] = i+1;//如果是新的字符就更新新的状态
                else
                    stat[i][c] = stat[X][c];//如果是不同的字符就返回上一个状态
            }
            //更新上一个状态X
            X = stat[X][needle.charAt(i)];
        }
        //创建好之后可以开始检索了
        int stat_num = 0;//当前状态
        for (int i = 0; i < haystack.length(); i++) {
            //获取下一个状态
            stat_num = stat[stat_num][haystack.charAt(i)];

            if (stat_num == needle.length())return i - needle.length()+1;
        }
        //没找到
        return -1;
    }


}




public class day1 {
    public static void main(String[] args) {
        System.out.println(new Rabin_Karp().soulorithm("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
        System.out.println(new KMP_().strStr("sadbutsad","s"));
    }
}
