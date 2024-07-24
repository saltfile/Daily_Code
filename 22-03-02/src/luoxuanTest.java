public class luoxuanTest {
    public static void main(String[] args) {
        int n = 3;
        int a = 1;
        int[][] awp = Xun(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(awp[i][j]+"   ");
            }
            System.out.println();
        }


    }


    public static int[][] Xun(int n){
        int start = 0;
        int flag = 0;
        int count = 1;  // 定义填充数字
        int[][] map = new int[n][n];
        int i,j;
        while (flag++ < n/2){
            //从左往右
            for (j = start; j < n - flag; j++) {
                map[start][j] = count++;
            }
            //从上向下
            for (i = start; i < n - flag; i++) {
                map[i][j] = count++;
            }

            for ( ; j >= flag ; j--) {
                map[i][j] = count++;
            }
            for (; i >= flag; i--) {
                map[i][j] = count++;
            }
            start++;
        }
        if (n % 2 == 1){
            map[start][start] = count;
        }
        return map;
    }


}
