package data_structure;
/**
 * @author saltfish
 * @date 2022-03-02
 * TODO:用java写的一个比较简陋的栈
 */
public class MyStack<T> {
    private T[] stackMem;
    private int stacklen = 0;
    private int top = -1;//栈顶
    private int bottom = 0;//栈底

    /**
     * @param a 表示栈的长度
     *
     */

    public MyStack(int a){
        this.stackMem = (T[]) new Object[a];
        this.stacklen = a;
    }

    /**
     * TODO:进栈
     * @param data 要入栈的数据
     */

    public void push(T data){
        top++;
        if(top == stacklen-1){
            System.out.println("扩容");
            MemExpansion();
        }
        this.stackMem[top] = data;
    }

    /**
     * TODO:弹栈
     * @return 返回栈顶的值并弹出
     */

    public T pop(){
        if(top == -1)return null;
        T res = this.stackMem[top];
        this.stackMem[top] = null;//手动释放
        System.gc();
        top--;
        return res;
    }

    /**
     * TODO：返回栈顶第一个元素并不弹出
     * @return 目标数据
     */
    public T popEi(){
        if(top == -1)return null;
        return this.stackMem[top];
    }

    /**
     * TODO:扩容
     */
    private void MemExpansion(){
        T[] arr2=java.util.Arrays.copyOf(this.stackMem,stacklen*2);
        this.stackMem = arr2;
        this.stacklen = stacklen*2;
    }

    /**
     * TODO:展示栈中所有元素
     */
    public void display(){
        for (Object t:this.stackMem){
            if(t == null)continue;
            System.out.println(t+"   ");

        }
    }

    /**
     * TODO:返回栈的元素长度
     * @return 栈长
     */
    public int count(){
        return top+1;
    }

    /**
     * TODO:查看栈是否为空
     * @return 空 = true,非空 = false
     */
    public boolean isEmpty(){
        return top == -1;
    }
}
class demos{
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>(3);
        stack.push(123);
        stack.push(111);
        stack.display();
        System.out.println( stack.pop());
        stack.display();
    }
}


