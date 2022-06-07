import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.logging.Handler;

public class TalkHandler {
    //管理登陆的handler
    public static void LoginHandler(Map<String,SocketChannel> serverMap, Map<SocketChannel,String> channelMap, SocketChannel socketChannel,Mes mes){
        try {
            //当确认是登录时获取map的信息
            if(serverMap.get(mes.getUser()) == null){
                String str =  "欢迎您"+mes.getUser()+"\n";
                serverMap.put(mes.getUser(), socketChannel);
                channelMap.put(socketChannel,mes.getUser());
                socketChannel.write(ByteBuffer.wrap(str.getBytes()));
            }else {
              //用户已经登陆过
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
    //xxxxx@oo
    public static void SendHandler(Map<String,SocketChannel> serverMap,Map<SocketChannel,String> channelMap,SocketChannel socketChannel,Mes mes){
        try {
        String[] messages = mes.getMes().split("@");
        if(messages.length < 2){
            if(messages[0].equals("exit")){
                CloseHandler(serverMap,channelMap,socketChannel);
                return;
            } else
                socketChannel.write(ByteBuffer.wrap("发送语句格式错误，请重新编辑\n".getBytes()));
                return;
        }
        String self = channelMap.get(socketChannel);
        for (int i = 1;i < messages.length;i++){
            String firend = messages[i];
            SocketChannel firendsocket =  serverMap.get(firend);
            if (firendsocket != null){
            String mess = self+":"+messages[0]+"\n";
            firendsocket.write(ByteBuffer.wrap(mess.getBytes()));
            }else {
                String miss = "未找到"+firend+"请确定此用户存在\n";
                socketChannel.write(ByteBuffer.wrap(miss.getBytes()));
            }

        }
        } catch (IOException e) {

        }

    }

    public static void CloseHandler(Map<String,SocketChannel> serverMap,Map<SocketChannel,String> channelMap, SocketChannel socketChannel){
        try {
            String user = channelMap.get(socketChannel);
            channelMap.remove(socketChannel);
            serverMap.remove(user);
            socketChannel.write(ByteBuffer.wrap("bye bye !<(￣v￣)>  \n".getBytes()));
            socketChannel.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }











}
