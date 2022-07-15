package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface MainPerson{
    void attact();
}
class Hero implements MainPerson{
    @Override
    public void attact() {
        System.out.println("普通攻击+1");
    }
}
class HeroWithAct implements InvocationHandler {
    private Object o = null;
    public HeroWithAct(Object o){
        this.o = o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("附加火焰伤害");
        method.invoke(o);
        return null;
    }
}

public class Paxos {
    public static void main(String[] args) {
        MainPerson p =(MainPerson) Proxy.newProxyInstance(MainPerson.class.getClassLoader(),new Class[]{MainPerson.class},new HeroWithAct(new Hero()));
        p.attact();
    }
}
