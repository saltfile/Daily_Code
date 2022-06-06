import lombok.Data;
enum status{
    LOGIN,
    SEND,
    RECEIVE,
    ADD_FRIENDS
}
@Data
public class Mes{
    private String user;
    private String mes;
    private status statu;
}
