package design_mode;

import java.util.LinkedList;

/**
 * @author saltfish
 * @date 22-04-23
 * TODO:设计模式----------->中介模式
 * 这个例子未来会添加不同例子,其实可以改成线程模式
 */
//中介
class MyBlockQueue{
    private LinkedList<String> arr=new LinkedList();

    public int getLimit() {
        return limit;
    }

    private int limit=0;
    public MyBlockQueue(int limit){
        this.limit=limit;
    }
    public void push(String str){
        arr.addFirst(str);
    }
    public String pop(){
        return arr.removeLast();
    }
    public int getSize(){
        return arr.size();
    }
}
// 生产者
class Product{
    public MyBlockQueue myBlockQueue;
    public Product(MyBlockQueue myBlockQueue){
        this.myBlockQueue=myBlockQueue;
    }
    public boolean pub(String str){
        if(myBlockQueue.getSize()>= myBlockQueue.getLimit()){
            return false;
        }else{
            myBlockQueue.push(str);
            return true;
        }
    }
}
//消费者
class Comsumer{
    public MyBlockQueue myBlockQueue;
    public Comsumer(MyBlockQueue myBlockQueue){
        this.myBlockQueue=myBlockQueue;
    }
    public String sub(){
        if(myBlockQueue.getSize()<=0){
            return null;
        }else{
            return myBlockQueue.pop();
        }
    }
}


public class Intermediary {
    public static void main(String[] args) {
        MyBlockQueue myBlockQueue=new MyBlockQueue(10);
        Product p=new Product(myBlockQueue);
        Comsumer c=new Comsumer(myBlockQueue);
        System.out.println(p.pub("1"));
        System.out.println(p.pub("2"));
        System.out.println(p.pub("3"));
        System.out.println(p.pub("4"));
        System.out.println(c.sub());
        System.out.println(c.sub());
        System.out.println(c.sub());
    }
}
