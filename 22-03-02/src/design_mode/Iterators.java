package design_mode;

/**
 * @author saltfish
 * @date 2022-04-10
 * TODO:设计模式------>迭代器模式
 * 这个模式的概念表现为:
 * 顺序访问一个聚合对象中的各种元素，而又不暴露该对象的内部表示
 * 就是举个小例子，比如一个单位的小数组
 *
 */

class Itor<E>{
    private E[] arr;
    private int cap;
    private int index = 0;
    public Itor(E[] arr,int cap){
        this.arr = arr;
        this.cap = cap;
    }
    public boolean hashnext(){
        if(this.index<=this.cap){
            return true;
        }
        return false;
    }
    public E next(){
        return arr[index++];
    }
}

interface Arr{
   public Itor Itartor();
}

class MyArr<E> implements Arr{
    private E[] arr;
    private int index=0;
    private int max;
    public MyArr(int max){
        this.max=max;
        this.arr= (E[])new Object[max];
    }
    public void add(E i){
        if(this.index<=this.max) {
            arr[this.index++] = i;
        }else{
            throw new ArrayIndexOutOfBoundsException();
        }
    }
    @Override
    public Itor<E> Itartor() {
        return new Itor(this.arr,this.index-1);
    }
}

public class Iterators {
    public static void main(String[] args) {
        MyArr<String> my = new MyArr(20);
        my.add("asd");
        my.add("4444");
        my.add("asdasd");
        Itor<String> Iro = my.Itartor();
        while (Iro.hashnext()){
            System.out.println(Iro.next());
        }
    }
}
