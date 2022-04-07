package design_mode;

import java.util.ArrayList;

/**
 * @author saltfish
 * @date 2022-03-02
 * TODO:设计模式--->备忘录模式
 * TODO:1.这里的备忘录模式并不能选择性回到某个状态其中设计思路跟ctrl+z的效果是一样的
 * TODO:2.说说优点：这个设计模式可以恢复状态机制类似于“后悔药”,并且封装后对外不暴露对象
 * TODO:3.说说缺点:这个设计模式太浪费资源了不同的状态表示要存不同的类
 */
/**
 * TODO :Memento代表的是备忘录中的内部状态仅仅用来记录角色使用各个状态
 */
class Memento<T> {
   private T data;
   public Memento(T data){
       this.data = data;
   }
    public T getData() {
        return data;
    }
}
/**
 * TODO:这里的是用来访问备忘录的角色负责修改和恢复
 */
class Originator<T>{
    private T data;


    public T getData() {
        return data;
    }
    public void show(){
        System.out.println("记录:"+data);
    }

    public void setData(T data) {
        this.data = data;
    }
    public void load(Memento s){
        this.data = (T) s.getData();
    }
}

/**
 * TODO:管理员用来提供和管理备忘录状态
 * @param <T>泛型的任意类型
 */
class Caretaker<T>{
    private ArrayList<Memento> arr = new ArrayList();
    public void save(Originator<T> O){
        Memento<T> memento = new Memento<>(O.getData());
        arr.add(memento);
    }
    public Originator<T> load(){
     Originator<T> ori = new Originator<>();
     int arrlen = arr.size()-1;
     Memento m = this.arr.get(arrlen);
     arr.remove(arrlen);
     ori.load(m);
        return ori;
    }

}

/**
 * TODO:主要使用的方法
 */
public class memo {
    //备忘录管理员
    static Originator<String> originator = new Originator<>();
    public static void main(String[] args) {
        //备忘录角色
     Caretaker<String> caretaker = new Caretaker<>();
     //保存当前状态
     originator.setData("aaa");
     caretaker.save(originator);
     //覆盖刚刚的备忘录信息
     originator.setData("bbb");
     originator.show();
     //还原刚刚的备忘录信息
     originator = caretaker.load();
     originator.show();
    }
}
