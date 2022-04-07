package design_mode;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author saltfish
 * @date 22-03-05
 * TODO:设计模式----->代理模式
 *
 */

interface Attack{
   public void attack();
}

class Gun implements Attack{

    @Override
    public void attack() {
        System.out.println("发出子弹造成20伤害");
    }
}

class FireMan implements InvocationHandler{
    Attack attack;
    public FireMan(Attack attack){
        this.attack = attack;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        attack.attack();
        System.out.println("附加+4点燃烧效果");
        return null;
    }
}

public class agent {
    public static void main(String[] args) {
        Attack attack = (Attack) Proxy.newProxyInstance(Attack.class.getClassLoader(),new Class[]{Attack.class}
        ,new FireMan(new Gun()));
        attack.attack();
    }
}
