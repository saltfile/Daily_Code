import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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



    @Override
    public void run() {

    }
}
