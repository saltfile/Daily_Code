import lombok.Data;

@Data
public class enitry {
    private String projectpath;
    public enitry(String uid){
        this.projectpath = uid;
    }
    public enitry(){}
    public enitry copy(){
        return new enitry(projectpath);
    }
}
