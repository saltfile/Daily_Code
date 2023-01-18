package aohiothor;


import java.util.*;

/**
 * 232. 用栈实现队列
 *
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 *
 * 实现 MyQueue 类：
 *
 *     void push(int x) 将元素 x 推到队列的末尾
 *     int pop() 从队列的开头移除并返回元素
 *     int peek() 返回队列开头的元素
 *     boolean empty() 如果队列为空，返回 true ；否则，返回 false
 *
 * 说明：
 *
 *     你 只能 使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 *     你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 */






class MyQueue {
    Stack<Integer> inStk = new Stack<>();
    Stack<Integer> outStk = new Stack<>();

    public MyQueue() {

    }
    //push 放进instk里面
    public void push(int x) {
        this.inStk.push(x);
    }
    //如果出栈空了的话就从inStk里面拿值
    public int pop() {
        if (outStk.empty()){
            //把inStk的值放到outStk里面
            while (!inStk.empty()){
                outStk.push(inStk.pop());
            }

        }
        return outStk.pop();
    }

    public int peek() {
        int res = pop();
        this.outStk.push(res);
        return res;
    }

    public boolean empty() {
        return inStk.empty()&& outStk.empty();
    }
}

/**
 * 225. 用队列实现栈
 *
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 *     void push(int x) 将元素 x 压入栈顶。
 *     int pop() 移除并返回栈顶元素。
 *     int top() 返回栈顶元素。
 *     boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 注意：
 *
 *     你只能使用队列的基本操作 —— 也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
 *     你所使用的语言也许不支持队列。 你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 */

class MyStack {


    Queue<Integer> outQueue = new LinkedList<>();
    Queue<Integer> inQueue = new LinkedList<>();

    public MyStack() {
    }

    public void push(int x) {
        inQueue.offer(x);
        while (!outQueue.isEmpty()){
            inQueue.offer(outQueue.poll());
        }
        Queue<Integer> temp;
        temp = inQueue;
        inQueue = outQueue;
        outQueue = temp;
    }

    public int pop() {
       return   outQueue.poll();
    }

    public int top() {
        return outQueue.peek();
    }

    public boolean empty() {
        return outQueue.isEmpty();
    }
}

/**
 * 20. 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 *     左括号必须用相同类型的右括号闭合。
 *     左括号必须以正确的顺序闭合。
 *     每个右括号都有一个对应的相同类型的左括号。
 * 使用匹配的方法就是栈'['对应被']'抵消，最后剩下栈长度为0括号就写完整了
 **/

class IsValid{
    public boolean isValid(String s) {
        Stack<Character> deque = new Stack<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            //碰到左括号，就把相应的右括号入栈
            if (ch == '(') {
                deque.push(')');
            }else if (ch == '{') {
                deque.push('}');
            }else if (ch == '[') {
                deque.push(']');
            } else if (deque.isEmpty() || deque.peek() != ch) {
                return false;
            }else {//如果是右括号判断是否和栈顶元素匹配
                deque.pop();
            }
        }
        //最后判断栈中元素是否匹配
        return deque.isEmpty();
    }
}

/**
 * 1047. 删除字符串中的所有相邻重复项
 *
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 */


class RemoveDuplicates{

    public String removeDuplicates(String s) {
        Stack<Character> Stk = new Stack<>();


        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Stk.isEmpty() || Stk.peek() != c){
                Stk.push(c);
            }else {
                Stk.pop();
            }
        }

        String res = "";
        while (!Stk.isEmpty()){
            res=Stk.pop()+res;
        }

        return res;
    }
    public String removeDuplicates2(String s) {
        StringBuilder Stk = new StringBuilder();


        int top = -1;//栈顶
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (top < 0 || Stk.charAt(top) != c) {
                Stk.append(c);
                top++;
            } else {
                Stk.deleteCharAt(top);
                top--;
            }
        }

        return new String(Stk);
    }
}


/**
 * 150. 逆波兰表达式求值
 *
 * 给你一个字符串数组 tokens ，表示一个根据逆波兰表示法 表示的算术表达式。
 *
 * 请你计算该表达式。返回一个表示表达式值的整数。
 *
 * 注意：
 *
 *     有效的算符为 '+'、'-'、'*' 和 '/' 。
 *     每个操作数（运算对象）都可以是一个整数或者另一个表达式。
 *     两个整数之间的除法总是 向零截断 。
 *     表达式中不含除零运算。
 *     输入是一个根据逆波兰表示法表示的算术表达式。
 *     答案及所有中间计算结果可以用 32 位 整数表示。
 */


class EvalRPN{
    public int evalRPN(String[] tokens) {
        Stack<Integer> Stk = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i].equals( "+")||tokens[i].equals( "*")||tokens[i].equals( "-")||tokens[i].equals( "/")){
                int num1 = Stk.pop();
                int num2 = Stk.pop();
                if (tokens[i].equals( "+"))Stk.push(num1+num2);
                if (tokens[i].equals( "-"))Stk.push(-num1+num2);
                if (tokens[i].equals( "*"))Stk.push(num1*num2);
                if (tokens[i].equals( "/"))Stk.push(num2/num1);

            }else {
                Stk.push(Integer.parseInt(tokens[i]));
            }
        }
        return Stk.pop();

    }
}


/**
 * 239. 滑动窗口最大值
 *
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */

//单调队列从大到小
class DanQueue {
    Deque<Integer> deque = new LinkedList<>();

    void poll(int val) {
        if (!deque.isEmpty() && val == deque.peek()) {
            deque.poll();
        }
    }

    void add(int val) {
        //如果val 比最后一个大就把最后一个给remove然后在加进去
        while (!deque.isEmpty() && val > deque.getLast()) {
            deque.removeLast();
        }
        deque.add(val);
    }
    //队列队顶元素始终为最大值
    int peek() {
        return deque.peek();
    }
}
class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) {
            return nums;
        }
        int res_len = nums.length-k+1;

        int[] res = new int[res_len];
        int idx = 0;
        //滑动窗口
        DanQueue IQu = new DanQueue();



        for (int i = 0; i < k; i++) {
            IQu.add(nums[i]);
        }
        res[idx++] = IQu.peek();

        for (int i = k; i < nums.length; i++) {

            IQu.poll(nums[i-k]);

            IQu.add(nums[i]);

            res[idx++] = IQu.peek();
        }
        return res;


    }
}








public class stack_queue_part {

    public static void main(String[] args) {
//        System.out.println(new IsValid().isValid("[]"));

//        System.out.println(new RemoveDuplicates().removeDuplicates2("abbaca"));

//        String[] token = new String[]{"4","13","5","/","+"};
//        System.out.println(new EvalRPN().evalRPN(token));

        int[] arr = new int[]{1,3,-1,-3,5,3,6,7};
        int[] arr1 = new MaxSlidingWindow().maxSlidingWindow(arr,3);

        for (int a:arr1){
            System.out.println(a);
        }


//        MyStack queue = new MyStack();
//        queue.push(1);
//        queue.push(2);
//        queue.pop();   // 注意弹出的操作
//        queue.push(3);
//        queue.push(4);
//        queue.pop();  // 注意弹出的操作
//        queue.pop();
//        queue.pop();
//        queue.empty();
    }








}
