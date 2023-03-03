package aohiothor;
// 散

//在一段字符串中找到最长的且不重复的字符串 都是小写字母

import java.util.ArrayList;

class soult1{
    public ArrayList<String> MaxBu(String str){
        int left = 0;
        int max = 0;
        ArrayList<String> arr = new ArrayList<>();
        for (int right = 1; right < str.length(); right++) {
            String st = str.substring(left,right);
            //最起码要大于前面的max
            if (st.length() > max){
                //如果是最长
                if (isMString(st)){
                    arr.clear();
                    arr.add(st);
                    max = st.length();
                }else {
                    left = right-1;
                }
            }else if(st.length() == max){
                if (isMString(st)){
                    arr.add(st);
                }
            }
        }
        return arr;

    }
    public boolean isMString(String s){
        int[] _tab = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i)-'a';
            if (_tab[idx] == 0){
                _tab[idx] = 1;
            }else {
                return false;
            }
        }
        return true;
    }
}











public class cont_part {
    public static void main(String[] args) {
        System.out.println(new soult1().MaxBu("stringsabcvdb"));
    }
}
