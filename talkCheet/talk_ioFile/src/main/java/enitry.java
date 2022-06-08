import lombok.Data;

@Data
public class enitry {
    private String uid;
    public enitry(String uid){
        this.uid = uid;
    }
    public enitry(){}
    public enitry copy(){
        return new enitry(uid);
    }
}
