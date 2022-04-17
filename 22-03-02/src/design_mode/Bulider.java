package design_mode;
/**
 * @author saltfish
 * @date 22-04-17
 * TODO:设计模式----------->建造者模式
 * 每个类构造的步骤都可以有建造者来实现
 * 举个例子就是建造玩家人物以及种类是不一样的，这里只是举一个简单例子
 */

interface Step{
    public void pace1();
    public void pace2();
    public void pace3();
    public void pace4();
}
//
class Wilson implements Step{

    @Override
    public void pace1() {
        System.out.println("名字:威尔逊");
    }

    @Override
    public void pace2() {
        System.out.println("特殊：每隔三天生长胡子");
    }

    @Override
    public void pace3() {
        System.out.println("道具：无");
    }

    @Override
    public void pace4() {
        System.out.println("体质：由于是科学家，智商上限200+");
    }
}

class Maxwell implements Step{

    @Override
    public void pace1() {
        System.out.println("名字:麦斯威尔");
    }

    @Override
    public void pace2() {
        System.out.println("特殊：可召唤黑暗护法");
        System.out.println("     比较弱的体质");
        System.out.println("     一个戏命师");
    }

    @Override
    public void pace3() {
        System.out.println("道具：黑暗之书，紫水晶，影刀，影甲");
    }

    @Override
    public void pace4() {
        System.out.println("体质：体质较弱，血量上限75+");
    }
}

class Build{
    private Step step;
    public Build(Step step){
        this.step = step;
    }
    public void build(){
        System.out.println("创建角色");
        step.pace1();
        step.pace2();
        step.pace3();
        step.pace4();
        System.out.println();
    }
}


public class Bulider {
    public static void main(String[] args) {
        Build w = new Build(new Wilson());
        Build m = new Build(new Maxwell());
        w.build();
        m.build();
    }
}
