package leecode;

/**
 * 实现myAtoi(string s)函数，将字符串转换为 32 位有符号整数（类似于 C/C++ 的atoi函数）。
 *
 * 算法myAtoi(string s)如下：
 *
 * 读入并忽略任何前导空格。
 * 检查下一个字符（如果还没有在字符串的末尾）是'-'或'+'。如果是，请读入此字符。这分别确定最终结果是负数还是正数。如果两者都不存在，则假设结果是肯定的。
 * 读入下一个字符，直到到达下一个非数字字符或输入结尾。字符串的其余部分被忽略。
 * 将这些数字转换为整数（即"123" -> 123​​ , "0032" -> 32）。如果没有读取任何数字，则整数为0。根据需要更改符号（从步骤 2 开始）。
 * 如果整数超出 32 位有符号整数范围，则将整数钳位以使其保持在该范围内。具体来说，小于的整数应该被限制为，大于的整数应该被限制为。[-231, 231 - 1]-231-231231 - 1231 - 1
 * 返回整数作为最终结果。
 */

class Solution8 {
    public int myAtoi2(String input) {
        int sign = 1;
        int result = 0;
        int index = 0;
        int n = input.length();

        // Discard all spaces from the beginning of the input string.
        while (index < n && input.charAt(index) == ' ') {
            index++;
        }

        // sign = +1, if it's positive number, otherwise sign = -1.
        if (index < n && input.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (index < n && input.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        // Traverse next digits of input and stop if it is not a digit
        while (index < n && Character.isDigit(input.charAt(index))) {
            int digit = input.charAt(index) - '0';

            // Check overflow and underflow conditions.
            if ((result > Integer.MAX_VALUE / 10) ||
                    (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                return sign == 1? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            // Append current digit to the result.
            result = 10 * result + digit;
            index++;
        }

        // We have formed a valid number without any overflow/underflow.
        // Return it after multiplying it with its sign.
        return sign * result;
    }
}





public class String_to_Integer {
    public static void main(String[] args) {
        System.out.println(new Solution8().myAtoi2("000000000000000-000000000000011"));
    }
}
