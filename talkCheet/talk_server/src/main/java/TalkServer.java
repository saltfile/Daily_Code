import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class TalkServer implements Runnable{
    private Map<String, SocketChannel> serverMap = new ConcurrentHashMap<>();
    // 用于检测所有Channel状态的Selector
    private Selector selector = null;
    //线程安全的计数器
    LongAdder count = new LongAdder();
    private static final int TIMEOUT = 3000;

    public void init(){
        try {
            selector = Selector.open();
            //服务通道绑定设置信息
            ServerSocketChannel server = ServerSocketChannel.open();
            InetSocketAddress address = new InetSocketAddress("localhost",12581);
            server.socket().bind(address);
            //设置非阻塞设置 false表示非阻塞
            server.configureBlocking(false);
            //制定注册的事件设置
            server.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                if (selector.select(TIMEOUT) == 0) {
                    continue;
                }
                for (SelectionKey selectedKey : selector.selectedKeys()) {
                    selector.selectedKeys().remove(selectedKey);
                    if (selectedKey.isAcceptable()) {
                        // 调用accept方法接受连接，产生服务器端对应的SocketChannel
                        SocketChannel sc = server.accept();
                        saveAndRegisterChannel(sc);
                    }
                    if (selectedKey.isReadable()) {
                        try {
                            ReadAndSendMessageToSelectChannel((SocketChannel) selectedKey.channel());
                        } catch (IOException ex) {
                            // 如果捕捉到该sk对应的Channel出现了异常，即表明该Channel
                            // 对应的Client出现了问题，所以从Selector中取消sk的注册
                            // 从Selector中删除指定的SelectionKey
                            selectedKey.cancel();
                            if (selectedKey.channel() != null) {
                                selectedKey.channel().close();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void saveAndRegisterChannel(SocketChannel socketChannel) throws IOException {
        if (socketChannel == null) {
            return;
        }
        // 设置采用非阻塞模式
        socketChannel.configureBlocking(false);
        // 将该SocketChannel也注册到selector
        socketChannel.register(selector, SelectionKey.OP_READ);
        count.increment();
        String currentId = count.toString();
        serverMap.put(currentId, socketChannel);
        
        for (SocketChannel channel : serverMap.values()) {
            channel.write(ByteBuffer.wrap(currentId.getBytes()));
        }
    }


    private void ReadAndSendMessageToSelectChannel(SocketChannel socketChannel) throws IOException {
        // 开始读取数据
        // 定义准备执行读取数据的ByteBuffer
        ByteBuffer buff = ByteBuffer.allocate(1024);
        StringBuilder content = new StringBuilder();

        //写buffer
        while (socketChannel.read(buff) > 0) {
            buff.flip();
            content.append(StandardCharsets.UTF_8.decode(buff));
        }
        // 打印从该sk对应的Channel里读取到的数据
        System.out.println("=====" + content);
        // 如果content的长度大于0，即聊天信息不为空
        if (content.length() > 0) {
            String[] split = content.toString().split("#");
            if (split.length < 2) {
                socketChannel.write(StandardCharsets.UTF_8.encode("格式错误"));
                return;
            }
            SocketChannel target = serverMap.get(split[0]);
            if (target == null) {
                socketChannel.write(StandardCharsets.UTF_8.encode("该账号已离线0"));
                return;
            }
            try {
                target.write(StandardCharsets.UTF_8.encode(split[1]));
            } catch (IOException e) {
                socketChannel.write(StandardCharsets.UTF_8.encode("该账号已离线1"));
                serverMap.remove(split[0]);
            }
        }
    }

    @Override
    public void run() {
        init();
    }
}
