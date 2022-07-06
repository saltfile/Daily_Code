package leecode;

import java.util.Stack;

class Solution20 {
    public boolean isValid(String s) {
        Stack<Character> openings = new Stack<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') {
                openings.push(c);
            } else if (c == '}' && !openings.empty() && openings.peek() == '{') {
                openings.pop();
            } else if (c == ')' && !openings.empty() && openings.peek() == '(') {
                openings.pop();
            } else if (c == ']' && !openings.empty() && openings.peek() == '[') {
                openings.pop();
            } else {
                return false;
            }

        }
        return openings.empty();
    }
}
public class ValidParentheses {
}
