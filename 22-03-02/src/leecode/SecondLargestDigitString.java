package leecode;

public class SecondLargestDigitString {
    public int secondHighest(String s) {
        char[] cs = s.toCharArray();


        int fir = -1;
        int dou = -1;

        for (char c:cs) {
            if (Character.isLetter(c)) continue;
            int num = c-'0';
            if (fir < num){
                dou = fir;
                fir = num;
            }else if (num > dou&&num < fir){
             dou = num;
            }
        }
        return dou;
    }


    public static void main(String[] args) {
        System.out.println(new SecondLargestDigitString().secondHighest("dfa12345621afd"));




    }
}
