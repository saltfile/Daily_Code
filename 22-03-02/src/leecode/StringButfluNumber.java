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
    public static void main(String[] args) {
        System.out.println(new StringButfluNumber().beautySum("aabcb"));
    }
}
