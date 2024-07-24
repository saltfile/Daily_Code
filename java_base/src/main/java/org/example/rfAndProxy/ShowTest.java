package org.example.rfAndProxy;

import java.util.TreeMap;

public class ShowTest {
    private static Object objA = new Object();
    private static Object objB = new Object();
    public static void main(String[] args) {













        new Thread(
                ()->{
                    try {
                    synchronized (objA) {
                        System.out.println(Thread.currentThread().getName() + "获取A");


                        System.out.println(Thread.currentThread().getName() + "等待B释放");
                        Thread.sleep(1000);
                        synchronized (objB) {
                            System.out.println(Thread.currentThread().getName() + "获取B");
                        }
                    }

                }catch (Exception e){
                        e.printStackTrace();
                    }
                }

        ).start();



        new Thread(
                ()->{
                    try {
                        synchronized (objB) {
                            System.out.println(Thread.currentThread().getName() + "获取B");


                            System.out.println(Thread.currentThread().getName() + "等待A释放");
                            Thread.sleep(1000);
                            synchronized (objA) {
                                System.out.println(Thread.currentThread().getName() + "获取A");
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

        ).start();









    }
    int cntOfTrees(int n) {
        int N = (int)3e3 + 5;
        int MOD = (int) 1e9 + 7;
        long[] dp = new long[N];
        // 节点个数为偶数时无法构成好二叉树
        if (n % 2 == 0) return 0;
        dp[1] = dp[3] = 1;
        for (int i = 5; i <= n; i += 2) {
            for (int j = 1, k = i - 1; j < k; j += 2) {
                dp[i] += (dp[j] * dp[k - j]) % MOD;
                dp[i] %= MOD;
            }
        }
        return (int) dp[n];
    }
}
