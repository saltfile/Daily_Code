package aohiothor;


import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

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


public class stack_queue_part {

    public static void main(String[] args) {
        System.out.println(new IsValid().isValid("[]"));





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
