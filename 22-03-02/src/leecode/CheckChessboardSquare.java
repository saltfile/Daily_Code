package leecode;

public class CheckChessboardSquare {
    static int[] arr = {4782969, 1594323, 531441, 177147, 59049, 19683, 6561, 2187, 729, 243, 81, 27, 9, 3, 1};
    public boolean checkPowersOfThree(int n) {
        for (int i:arr) {
            if (n >= i){
                n-=i;
            }
        }
        return n == 0;
    }
    public static boolean squareIsWhite(String coordinates) {
        return ((coordinates.charAt(0)-'a'+1)+(coordinates.charAt(1)-'0'))%2 == 1;
    }

    public static void main(String[] args) {
        System.out.println(new CheckChessboardSquare().checkPowersOfThree(9));
    }

}
