package leecode;
  //今天的签到题1769
public class Move_ball {
    public static int[] minOperations(String boxes) {
        int[] res = new int[boxes.length()];
        for (int i = 0; i < boxes.length(); i++) {
            int num = 0;
            for (int j = 0; j < boxes.length(); j++) {
                if (boxes.charAt(j) == '1'){
                    num+=Math.abs(j-i);
                }
                res[i] = num;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] ptr = minOperations("001011");






        System.out.println("Hello world!");
    }
}
