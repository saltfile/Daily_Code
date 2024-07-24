import java.util.ArrayList;
import java.util.Arrays;

public class TestMeet {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int[] num2 = {1, 2, 3};

        int arrLen = nums.length + num2.length;

        int[] sum = new int[arrLen];

        int flag = 0;
        int slow = 0;
        int fast = 0;

        while (slow < nums.length || fast < num2.length) {
            if (fast < num2.length && slow < nums.length) {
                if (nums[slow] < num2[fast]) {
                    sum[flag] = nums[slow];
                    slow++;
                    flag++;
                    continue;
                }else {
                    sum[flag] = num2[fast];
                    fast++;
                    flag++;
                    continue;
                }
            }
            if (fast >= num2.length && slow < nums.length){
                sum[flag] = nums[slow];
                slow++;

            }
            if (slow >= nums.length && fast < num2.length){
                sum[flag] = num2[fast];
                fast++;

            }
            flag++;

        }

        for (int i = 0; i < sum.length; i++) {
            System.out.println(sum[i]);
        };

    }


}
