package design_mode;

import java.util.HashMap;

/**
 * @author saltfish
 * @date 22-04-15
 * TODO:设计模式----------->状态模式
 * 就是我们常说的状态机，为了避免很多的if判断
 */

enum StateTemp{
    START,OK,ERR,STOP
}
class Process_Stat{
    public StateTemp currentStat;
    public Process_Stat ok;
    public Process_Stat start;
    public Process_Stat err;
    public Process_Stat stop;
    public Process_Stat(StateTemp status){
        this.currentStat=status;
    }
}
class States{
    private Process_Stat currentStat;
    public Process_Stat getCurrentStat() {
        return currentStat;
    }
    public void setCurrentStat(Process_Stat currentStat) {
        this.currentStat = currentStat;
    }
    @Override
    public String toString() {
        return this.currentStat.currentStat.toString();
    }
}
class ProThread{
    public static Process_Stat head=new Process_Stat(StateTemp.START);
    static{
        Process_Stat ok=new Process_Stat(StateTemp.OK);
        head.ok=ok;
        head.err=new Process_Stat(StateTemp.ERR);
        head.start = new Process_Stat(StateTemp.START);
        head.stop = new Process_Stat(StateTemp.ERR);
        ok.ok=new Process_Stat(StateTemp.START);
        ok.err=new Process_Stat(StateTemp.STOP);
        ok.start = new Process_Stat(StateTemp.OK);
        ok.stop = new Process_Stat(StateTemp.ERR);
    }
    private HashMap<String,States> process=new HashMap<>();
    public void commit(String name){
        States state = new States();
        state.setCurrentStat(head);
        process.put(name,state);
    }
    public void next_Ok(String name){
        States s=process.get(name);
        Process_Stat tmp=s.getCurrentStat();
        tmp=tmp.ok;
        States s2=new States();
        s2.setCurrentStat(tmp);
        process.put(name,s2);
    }
    public void next_Err(String name){
        States s=process.get(name);
        Process_Stat tmp=s.getCurrentStat();
        tmp=tmp.err;
        States s2=new States();
        s2.setCurrentStat(tmp);
        process.put(name,s2);
    }
    public void next_Start(String name){
        States s=process.get(name);
        Process_Stat tmp=s.getCurrentStat();
        tmp=tmp.start;
        States s2=new States();
        s2.setCurrentStat(tmp);
        process.put(name,s2);
    }

    public void next_Stop(String name){
        States s=process.get(name);
        Process_Stat tmp=s.getCurrentStat();
        tmp=tmp.stop;
        States s2=new States();
        s2.setCurrentStat(tmp);
        process.put(name,s2);
    }
    public States getStatus(String name){
        States s=process.get(name);
        return s;
    }
}

public class State {
    public static void main(String[] args) {
        ProThread pro = new ProThread();
        pro.commit("xxx");
        States s1 = pro.getStatus("xxx");
        System.out.println(s1.toString());
        pro.next_Ok("xxx");
        States s2 = pro.getStatus("xxx");
        System.out.println(s2.toString());
        pro.next_Err("xxx");
        States s3 = pro.getStatus("xxx");
        System.out.println(s3.toString());
        pro.next_Stop("xxx");
        States s4 = pro.getStatus("xxx");
        System.out.println(s4.toString());
    }
}
