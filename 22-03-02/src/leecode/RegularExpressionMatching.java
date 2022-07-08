package leecode;

/**
 * 正则表达式匹配
 * 给定一个输入字符串s 和一个模式p，实现正则表达式匹配，支持'.'和'*'where：
 * '.'匹配任意单个字符。
 * '*'匹配零个或多个前面的元素。
 * 匹配应覆盖整个输入字符串（不是部分）。
 */
class Solution10 {
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*')
        {
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else
        {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }
}
public class RegularExpressionMatching {
    public static void main(String[] args) {
        System.out.println(new Solution10().isMatch("aa","ab"));

    }
}
