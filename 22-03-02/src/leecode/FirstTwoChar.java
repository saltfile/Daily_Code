package leecode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给你一个由小写英文字母组成的字符串 s ，请你找出并返回第一个出现 两次 的字母。
 *
 * 注意：
 *
 *     如果 a 的 第二次 出现比 b 的 第二次 出现在字符串中的位置更靠前，则认为字母 a 在字母 b 之前出现两次。
 *     s 包含至少一个出现两次的字母。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/first-letter-to-appear-twice
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FirstTwoChar {

    public char repeatedCharacter(String s) {
        HashMap<Character,Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == null){
                map.put(s.charAt(i),s.charAt(i));
                continue;
            }else {
              return s.charAt(i);
            }
        }
        return ' ';
    }

    public static void main(String[] args) {

    }

}
