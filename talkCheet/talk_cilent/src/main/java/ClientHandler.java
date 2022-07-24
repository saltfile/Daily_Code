import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientHandler {
    public static void LoginHandler(SocketChannel socketChannel,String username){
        try {
            Mes mes = new Mes();
            mes.setStatu(status.LOGIN);
            mes.setUser(username);
            socketChannel.write(ByteBuffer.wrap(MesCode.Encoder(mes).getBytes()));
        }catch (Exception e){

        }
    }
    public static void SendHandler(SocketChannel socketChannel,String message,String username){
        try{
            Mes mes = new Mes();
            mes.setStatu(status.SEND);
            mes.setUser(username);
            mes.setMes(message);
            socketChannel.write(ByteBuffer.wrap(MesCode.Encoder(mes).getBytes()));
        }catch (Exception e){
        }
    }

    public static void AddFirendHandler(SocketChannel socketChannel,String message,String username){
        try{
            Mes mes = new Mes();
            mes.setStatu(status.ADD_FRIENDS);
            mes.setUser(username);
            mes.setMes(message);
            socketChannel.write(ByteBuffer.wrap(MesCode.Encoder(mes).getBytes()));
        }catch (Exception e){

        }
    }

    public static void AassentFirendHandler(SocketChannel socketChannel,String message,String username){
        try {
            Mes mes = new Mes();
            mes.setStatu(status.ASSENT_FRIENDS);
            mes.setUser(username);
            mes.setMes(message);
            socketChannel.write(ByteBuffer.wrap(MesCode.Encoder(mes).getBytes()));
        }catch (Exception r){

        }
    }





}
