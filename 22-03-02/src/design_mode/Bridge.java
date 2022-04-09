package design_mode;

/**
 * @author saltfish
 * @date 2022-04-09
 * TODO:设计模式------->桥模式
 * 怎么说呢，有点像代理但又不是完全是代理
 * 这次例子就举个游戏中遇到的场景吧
 * 就拿游戏技能举个例子
 */

interface Basics{
  public void attack();
}
class Melee implements Basics{

    @Override
    public void attack() {
        System.out.print("悟净·[木]·近战[基础攻击3]");
    }
}

class LongRange implements Basics{
    @Override
    public void attack() {
        System.out.print("悟净·[木]·远程[基础攻击1]");
    }
}

class Skill{
    protected Basics basics;
    public Skill(Basics basics){
        this.basics = basics;
    }

    public void attack(){
        basics.attack();
    }
}

class Control extends Skill{

    public Control(Basics basics) {
        super(basics);
    }

    @Override
    public void attack() {
        super.attack();
        System.out.println("附魔伤害造成：4点伤害，附加4s眩晕");
    }
}

class Bleeding extends Skill{
    public Bleeding(Basics basics) {
        super(basics);
    }

    @Override
    public void attack() {
        super.attack();
        System.out.println("流血伤害造成：2点伤害，附加每秒3点流血效果，持续4秒");
    }
}

public class Bridge {
    public static void main(String[] args) {
        Skill s1 = new Bleeding(new LongRange());
        s1.attack();
        Skill s2 = new Control(new Melee());
        s2.attack();
    }
}
