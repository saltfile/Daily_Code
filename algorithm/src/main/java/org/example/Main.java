package org.example;

public class Main {
    public static int nearestValidPoint(int x, int y, int[][] points) {
        int n = points.length;
        int best = Integer.MAX_VALUE, bestid = -1;
        for (int i = 0; i < n; ++i) {
            int px = points[i][0], py = points[i][1];
            if (x == px) {
                int dist = Math.abs(y - py);
                if (dist < best) {
                    best = dist;
                    bestid = i;
                }
            } else if (y == py) {
                int dist = Math.abs(x - px);
                if (dist < best) {
                    best = dist;
                    bestid = i;
                }
            }
        }
        return bestid;
    }
    public static void main(String[] args) {
        int[][] arr = {{1,2},{3,1},{2,4},{2,3},{4,4}};
        System.out.println(nearestValidPoint(3,4,arr));

        System.out.println("Hello world!");
    }
}