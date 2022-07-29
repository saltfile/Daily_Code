package leecode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个字符串列表words和一个字符串pattern，返回该匹配项的列表 。您可以按任何顺序返回答案。words[i] pattern
 *
 * 如果存在字母排列，则一个单词与该模式匹配，因此在将模式中的p每个字母替换为 后，我们就得到了所需的单词。xp(x)
 *
 * 回想一下，字母的排列是从字母到字母的双射：每个字母都映射到另一个字母，没有两个字母映射到同一个字母
 * 例如：
 * 输入： words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * 输出： ["mee","aqq"]
 * 解释： "mee " 匹配模式，因为存在排列 {a -> m, b -> e, ...}。
 * "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列，因为 a 和 b 映射到同一个字母。
 */



class Solution890 {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        ArrayList<String> res = new ArrayList<>();
        for(String o:words){
            if (Replace(o,pattern))
                res.add(o);
        }
      return res;

    }

    private boolean Replace(String o,String pattern) {
        HashMap<Character,Character> o1 = new HashMap<>();
        HashMap<Character,Character> p1 = new HashMap<>();

        //如果pattern == mee o == abc
        //o1和p1对比每个单词的字母是否复合规律，如果相同的k将会被覆盖
        for(int i = 0 ;i < o.length();i++){
            Character ow = o.charAt(i);
            Character pw = pattern.charAt(i);
            if (!(o1.containsKey(ow)))
                o1.put(ow,pw);
            if (!(p1.containsKey(pw)))
                p1.put(pw,ow);
            if (o1.get(ow) != pw||p1.get(pw) != ow)
                return false;
        }
        return true;
    }





}






public class FindandReplacePattern {
    public static void main(String[] args) {
        String[] res = {"abc","deq","mee","aqq","dkd","ccc"};
        System.out.println(new Solution890().findAndReplacePattern(res,"abb"));
    }
}
