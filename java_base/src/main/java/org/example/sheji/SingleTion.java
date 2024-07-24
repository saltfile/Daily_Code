package org.example.sheji;

public class SingleTion {
    private volatile static SingleTion single;
    private SingleTion(){}

    public static SingleTion getInstance(){
        if(single == null){
            synchronized (SingleTion.class){
                if(single == null){
                single = new SingleTion();
                }
            }
        }
        return single;
    }

}
