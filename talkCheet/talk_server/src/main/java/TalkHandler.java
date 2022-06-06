import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;

public class TalkHandler {
    public static void LoginHandler(Map<String,SocketChannel> serverMap, Map<SocketChannel,String> channelMap, SocketChannel socketChannel,Mes mes){
        try {

            if(serverMap.get(mes.getUser()) == null){
                String str =  "欢迎您"+mes.getUser()+"\n";
                serverMap.put(mes.getUser(), socketChannel);
                channelMap.put(socketChannel,mes.getUser());
                for (SocketChannel channel : serverMap.values()) {
                        channel.write(ByteBuffer.wrap(str.getBytes()));
                }
            }else {
                String str = "已经目前用户已登录";
                socketChannel.write(ByteBuffer.wrap(str.getBytes()));
                socketChannel.close();
            }

        } catch (IOException e) {
            //如果登录失败需要关闭此通道
            String str = "error!!!!!请发布正确信息\n";

            try {
                socketChannel.write(ByteBuffer.wrap(str.getBytes()));
                socketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }


    }



}
