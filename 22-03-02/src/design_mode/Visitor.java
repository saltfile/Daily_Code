package design_mode;

import java.util.ArrayList;

/**
 * @author saltfish
 * @date 22-04-18
 * TODO:设计模式----------->访问者模式
 * 这个例子未来会添加不同例子
 */
interface vistor{
    void visit(Com com);
}

class vistor1 implements vistor {
    @Override
    public void visit(Com com) {
        System.out.println("1,被"+com.getName()+"访问");
        for(Com coms : com.getAbsComs()){
            coms.accept();
        }
    }
}
class vistor2 implements vistor {
    @Override
    public void visit(Com com) {
        System.out.println("2,被"+com.getName()+"访问");
        for(Com coms : com.getAbsComs()){
            coms.accept();
        }
    }
}



abstract class Com{
    private String name;
    protected vistor vistor;
    private ArrayList<Com> absComs=new ArrayList<>();
    abstract public void accept();

    public Com(vistor vistor){
        this.vistor = vistor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public ArrayList<Com> getAbsComs() {
        return absComs;
    }
    public void add(Com com) {
        this.absComs.add(com);
    }
}

class Com1 extends Com {

    public Com1(vistor visitor) {
        super(visitor);
    }
    @Override
    public void accept() {
        super.vistor.visit(this);
    }
}


public class Visitor {
    public static void main(String[] args) {
        vistor1 visitor1=new vistor1();
        vistor2 visitor2=new vistor2();
        Com com1 = new Com1(visitor1);
        com1.setName("aaa");
        Com com2 = new Com1(visitor2);
        com2.setName("bbb");
        Com com3 = new Com1(visitor1);
        com3.setName("ccc");
        com1.add(com2);
        com2.add(com3);
        com1.accept();

    }
}
