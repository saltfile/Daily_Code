package leecode;

public class StringButfluNumber {
    public int beautySum(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] cnums = new int[26];
            int max = 0;
            for (int j = i; j < s.length(); j++) {
                cnums[s.charAt(j)-'a']++;
                max = Math.max(max,cnums[s.charAt(j)-'a']);
                int min = s.length();

                for (int k = 0; k < 26; k++) {
                    if (cnums[k] > 0)
                    min = Math.min(min,cnums[k]);
                }
                res += max - min;
            }
        }
        return res;

    }
    public boolean checkIfPangram(String sentence) {
        int[] arr = new int[26];
        for (char c : sentence.toCharArray()){
            int a = c-'a';
            arr[a] = 1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]!=1)return false;
        }
        return true;
    }


    public int getLucky(String s, int k) {
        String cs = "";
        for (char c:s.toCharArray()) {
            int a = c-96;
            cs += Integer.toString(a);
        }
        int res = 0;
        for (char c : cs.toCharArray()){
            res+=c-'0';
        }
        k--;
        while(k > 0){
            int tem = res;
            res = 0;
            while(tem > 0){
                res += tem % 10;
                tem /= 10;
            }
            --k;
        }
        return res;
    }
    public static void main(String[] args) {
//        System.out.println(new StringButfluNumber().beautySum("aabcb"));
        System.out.println(new StringButfluNumber().getLucky("iiii",1));
    }
}
