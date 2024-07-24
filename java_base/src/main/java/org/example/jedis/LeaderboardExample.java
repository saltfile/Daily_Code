package org.example.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

public class LeaderboardExample {

    private static final String LEADERBOARD_KEY = "leaderboard";

    public static void main(String[] args) {
        // 创建 Jedis 连接
        Jedis jedis = new Jedis("localhost");

        // 添加用户分数
        addUserScore(jedis, "player1", 100);
        addUserScore(jedis, "player2", 80);
        addUserScore(jedis, "player3", 120);
        addUserScore(jedis, "player4", 90);

        // 获取排行榜
        Set<Tuple> leaderboard = getLeaderboard(jedis);

        // 打印排行榜
        System.out.println("Leaderboard:");
        int rank = 1;
        for (Tuple tuple : leaderboard) {
            String member = tuple.getElement();
            double score = tuple.getScore();
            System.out.println(rank + ". " + member + " - " + score);
            rank++;
            
        }

        // 关闭 Jedis 连接
        jedis.close();
    }

    private static void addUserScore(Jedis jedis, String player, double score) {
        jedis.zadd(LEADERBOARD_KEY, score, player);
    }

    private static Set<Tuple> getLeaderboard(Jedis jedis) {
        // 使用 ZREVRANGE 获取逆序排行榜（分数高到低）
        return jedis.zrevrangeWithScores(LEADERBOARD_KEY, 0, -1);
    }
}