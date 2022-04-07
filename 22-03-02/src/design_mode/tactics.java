package design_mode;

/**
 * @author saltfish
 * @date 2022-03-03
 * TODO:设计模式------>策略模式
 *
 */

/**
 * TODO:策略角色
 */
interface Strategys{
    public void algorithmFun();
}

class A implements Strategys{

    @Override
    public void algorithmFun() {
        System.out.println("我是算法功能A");
    }
}
class B implements Strategys{

    @Override
    public void algorithmFun() {
        System.out.println("我是算法功能B");
    }
}
class C implements Strategys{

    @Override
    public void algorithmFun() {
        System.out.println("我是算法功能C");
    }
}

/**
 * TODO:策略封装对象
 */
class Context{
    Strategys strategys;
    public Context(Strategys strategys){
        this.strategys = strategys;
    }

    public void fun(){
        strategys.algorithmFun();
    }

}


public class tactics {
    public static void main(String[] args) {
        Context context = new Context(new A());
        context.fun();
        context = new Context(new B());
        context.fun();
        context = new Context(new C());
        context.fun();
    }
}
