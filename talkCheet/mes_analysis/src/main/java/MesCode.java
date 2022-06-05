import com.alibaba.fastjson.JSON;

public class MesCode {
    //编码器
    public static String Encoder(Mes mes){
        return JSON.toJSONString(mes);
    }
    //解码器
    public static Mes Decoder(String json){
        return JSON.parseObject(json,Mes.class);
    }

}
