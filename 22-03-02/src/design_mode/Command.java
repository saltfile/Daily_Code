package design_mode;

/**
 * @author saltfish
 * @date 22-04-22
 * TODO:设计模式----------->命令模式
 */
enum Status{
    Attack,Defense
}

class comm{
    private Status status;
    public comm(Status status){
        this.status = status;
    }

    public String exec(){
        //执行命令的后果
        if (this.status == Status.Attack){return "使用攻击";}
        if (this.status == Status.Defense){return "使用防御";}
        return "";
    }
}

class GamePerson{
    public void fun(comm c){
        System.out.println(c.exec());
    }
}



public class Command {
    public static void main(String[] args) {
        GamePerson p = new GamePerson();
        p.fun(new comm(Status.Attack));
        p.fun(new comm(Status.Defense));
    }
}
