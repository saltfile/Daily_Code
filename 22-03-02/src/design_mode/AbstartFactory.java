package design_mode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @author saltfish
 * @date 22-03-07
 * TODO:设计模式----->抽象工厂
 * 案例思路:让一个工厂可以制造出剑&弓
 */
//下面可以使用两种产品
interface Sword{
    public void attack();
}

interface Bow{
    public void attack();
}

class HugeSword implements Sword{
    public String name;
    public HugeSword(String name){
        this.name = name;
    }
    @Override
    public void attack() {
        System.out.println("玩家"+name+"获得巨剑attack:20❤ effect:nul");
    }
}

class DaggerSword implements Sword{
    public String name;
    public DaggerSword(String name){
        this.name = name;
    }
    @Override
    public void attack() {
        System.out.println("玩家"+name+"获得短剑attack:-3*6❤ effect:4点流血效果");
    }
}

class IceBow implements Bow{
    String name;
    public IceBow(String name){
        this.name = name;
    }
    @Override
    public void attack() {
        System.out.println("玩家"+name+"获得冰箭attack:10❤ effect:眩晕+5s");
    }
}

class FireBow implements Bow{
    String name;

    @Override
    public void attack() {
        System.out.println("玩家"+name+"获得火箭attack:15❤ effect:4s燃烧效果");
    }
}
//这里用了两种剑一个是巨剑一个是双短剑利用反射生成实例化剑对象
class SwordFactory extends WeaponFactory{
    public static HashMap<String,Class> types = new HashMap<>();
    static {
        types.put("hugesword",HugeSword.class);
        types.put("daggersword",DaggerSword.class);
    }
    @Override
    public Object product(String type, String name) throws Exception {
        Class clz = types.get(type);
        Constructor con = clz.getConstructor(String.class);
        return con.newInstance(name);
    }
}
//这里我用了两种弓一个是冰一个是火弓这里依然如法炮制利用反射生成实例化对象
class BowFactory extends WeaponFactory{
    public static HashMap<String,Class> types = new HashMap<>();
    static {
        types.put("icebow",IceBow.class);
        types.put("firebow",FireBow.class);
    }
    @Override
    public Object product(String type, String name) throws Exception {
        Class clz = types.get(type);
        Constructor con = clz.getConstructor(String.class);
        return con.newInstance(name);
    }
}

/**
 * TODO:先是一个工厂，工厂可以变成两种类型剑工厂和弓工厂
 *
 */
abstract class WeaponFactory{
    public static HashMap<String,Class> types = new HashMap<>();
    static {
    types.put("sword",SwordFactory.class);
    types.put("bow",BowFactory.class);
    }
    //通过反射实例化完成一个对象,来决定这个工厂可以生产什么
    public static WeaponFactory creatFactory(String type) throws Exception {
        Class cl = types.get(type);
        return (WeaponFactory) cl.newInstance();
    }
    abstract public Object product(String type,String name) throws Exception;
}

public class AbstartFactory {
    public static void main(String[] args) throws Exception {
        WeaponFactory factory = WeaponFactory.creatFactory("sword");
        Sword sword = (Sword) factory.product("hugesword","aaa");
        sword.attack();
    }
}
