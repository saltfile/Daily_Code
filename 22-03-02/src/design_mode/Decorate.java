package design_mode;
interface IDecro{
    String printline();
}
class Coms implements IDecro{
    private String line;
    public Coms(String line){
        this.line=line;
    }
    public String printline(){
        return this.line;
    }
}
class DecroSharp implements IDecro{
    private IDecro decro;
    public DecroSharp(IDecro decro){
        this.decro=decro;
    }
    public String printline(){
        return "#"+this.decro.printline()+"#";
    }
}
class Decroplus implements IDecro{
    private IDecro decro;
    public Decroplus(IDecro decro){
        this.decro=decro;
    }
    public String printline(){
        return "+"+this.decro.printline()+"+";
    }
}
class Decrosub implements IDecro{
    private IDecro decro;
    public Decrosub(IDecro decro){
        this.decro=decro;
    }
    public String printline(){
        return "-"+this.decro.printline()+"-";
    }
}
public class Decorate {
    public static void main(String[] args) {
        System.out.println(new DecroSharp(new DecroSharp(new DecroSharp(new DecroSharp(new Coms("hello"))))).printline());
    }
}
