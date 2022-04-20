package design_mode;

import java.util.ArrayList;

/**
 * @author saltfish
 * @date 22-04-20
 * TODO:设计模式----------->观察者模式
 * 这个例子未来会添加不同例子
 */

class Event{
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    private int x;
}

abstract class IListener{
    public Event getEvent() {
        return e;
    }

    private Event e;
    public IListener(Event e){
        this.e=e;
    }
    abstract public void onEvent();
}

class MyListener extends IListener{

    public MyListener(Event e) {
        super(e);
    }

    @Override
    public void onEvent() {
        Event e=this.getEvent();
        System.out.println("MyListener:"+e.getX());
    }
}


class MyListener1 extends IListener{

    public MyListener1(Event e) {
        super(e);
    }

    @Override
    public void onEvent() {
        Event e=this.getEvent();
        System.out.println("MyListener1:"+e.getX());
    }
}

class Trigger{
    private ArrayList<IListener> arrayList=new ArrayList<>();
    public void addListener(IListener listener){
        this.arrayList.add(listener);
    }
    public void doEvent(Event e){
        for (IListener listener:this.arrayList){
            listener.onEvent();
        }
    }
}


public class Observer {
    public static void main(String[] args) {
        Event e=new Event();
        e.setX(12);
        Trigger t=new Trigger();
        t.addListener(new MyListener(e));
        //被观察
        t.addListener(new MyListener1(e));
        t.doEvent(e);
    }
}
