import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TalkCilent implements Runnable {
    private String username;
    // 定义检测SocketChannel的Selector对象
    private Selector selector = null;
    // 客户端SocketChannel
    private SocketChannel sc = null;

    public TalkCilent(String name) {
        this.username = name;

    }

    private void init() {
        try {
            selector = Selector.open();
            InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 12581);
            // 调用open静态方法创建连接到指定主机的SocketChannel
            sc = SocketChannel.open(isa);
            // 设置该sc以非阻塞方式工作
            sc.configureBlocking(false);
            // 将SocketChannel对象注册到指定Selector
            int op = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
            sc.register(selector, op);
            // 启动读取服务器端数据的线程
            new Thread(new Read()).start();
            ClientHandler.LoginHandler(sc, username);
            // 创建键盘输入流
            Scanner scan = new Scanner(System.in);
            while (scan.hasNextLine()) {
                // 读取键盘输入
                String line = scan.nextLine();
                String message = line.substring(2);
                //整理输入中的信息到底是哪一种格式然后通过handler打包
                String[] mess = line.split(":");
                switch (mess[0]){
                    case "0":ClientHandler.LoginHandler(sc,username);
                    case "1":ClientHandler.SendHandler(sc,message,username);
                    case "2":ClientHandler.AddFirendHandler(sc,message,username);
                    case "4":ClientHandler.AassentFirendHandler(sc,message,username);
                }
                ClientHandler.SendHandler(sc, line, username);
            }
        } catch (Exception e) {
            System.err.println("客户端异常发送");
        }
    }

    @Override
    public void run() {
        init();
    }


    private class Read implements Runnable {
        int len = 0;

        @Override
        public void run() {
            try {
                while (selector.select() > 0) {
                    // 遍历每个有可用IO操作Channel对应的SelectionKey
                    for (SelectionKey sk : selector.selectedKeys()) {
                        // 删除正在处理的SelectionKey
                        selector.selectedKeys().remove(sk);
                        // 如果该SelectionKey对应的Channel中有可读的数据
                        if (sk.isReadable()) {
                            // 使用NIO读取Channel中的数据
                            SocketChannel sc = (SocketChannel) sk.channel();
                            ByteBuffer buff = ByteBuffer.allocate(1024);
                            String content = "";

                            while ((this.len = sc.read(buff)) > 0) {
                                sc.read(buff);
                                buff.flip();
                                content += StandardCharsets.UTF_8.decode(buff);
                            }
                            if (len == -1) {
                                sc.close();
                                selector.close();
                                System.out.println("服务端连接断开");
                            }
                            // 打印输出读取的内容
                            System.out.println("聊天信息：" + content);
                        }
                    }
                }
            } catch (Exception e) {

            }
        }
    }

}
