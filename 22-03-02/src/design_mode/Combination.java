package design_mode;


import java.util.HashMap;

/**
 * @author saltfish
 * @date 22-04-18
 * TODO:设计模式----------->组合模式
 * 这个例子未来会添加不同例子
 */



class E extends Object implements Cloneable{
    private int color;
    private String name;
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public E clone() throws CloneNotSupportedException {
        return (E) super.clone();
    }
}

class Fac{
    private static HashMap<String,E> hm=new HashMap<>();
    public static void addType(String type,E t){
        hm.put(type,t);
    }
    public static E createT(String type) throws CloneNotSupportedException {
        return (hm.get(type)).clone();
    }
}







public class Combination {
    public static void main(String[] args) throws CloneNotSupportedException {
        E t1=new E();
        t1.setColor(1);
        E t2=new E();
        t2.setColor(2);
        E t3=new E();
        t3.setColor(3);
        Fac.addType("red",t1);
        Fac.addType("blue",t2);
        Fac.addType("green",t3);
        E tt=Fac.createT("red");
        tt.setName("aaaa");
        E tt3=Fac.createT("red");
        tt3.setName("xxx");
        E tt2=Fac.createT("blue");
        tt2.setName("bbb");
        System.out.println(tt.getColor()+"\t"+tt.getName());
        System.out.println(tt3.getColor()+"\t"+tt3.getName());
        System.out.println(tt2.getColor()+"\t"+tt2.getName());
    }
}
