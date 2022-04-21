package design_mode;



/**
 * @author saltfish
 * @date 22-04-20
 * TODO:设计模式----------->享元模式
 * 共享物件，用来尽可能减少内存使用量以及分享资讯给尽可能多的相似物件
 * 签到用
 */

class Method_Area{
    private static Method_Area method_area=new Method_Area();
    private Method_Area(){
        System.out.println("构造");
    }
    public static Method_Area getInstence(){
        return method_area;
    }
    public void x1(Heap_Obj obj){
        System.out.println(obj.getId());
    }
    public void x2(Heap_Obj obj){
        System.out.println(obj.getName());
    }
}
class Heap_Obj {
    public static Method_Area getMethod_area() {
        return method_area;
    }

    private static Method_Area method_area;
    static{
        method_area=Method_Area.getInstence();
    }
    public void dis1(){
        method_area.x1(this);
    }
    public void dis2(){
        method_area.x2(this);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;
}

public class EnjoYuan {
    public static void main(String[] args) {
        Heap_Obj heap=new Heap_Obj();
        Heap_Obj heap2=new Heap_Obj();
        heap.setId(11);
        heap.setName("xxx");
        heap2.setId(22);
        heap2.setName("yyy");
        heap.dis1();
        heap2.dis1();
        heap.dis2();
        heap2.dis2();
        heap2.setId(2332);
        heap2.dis1();
    }
}
