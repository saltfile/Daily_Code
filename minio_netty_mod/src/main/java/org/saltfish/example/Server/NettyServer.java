package org.saltfish.example.Server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.saltfish.example.Server.initializer.ServersInitializer;
import org.saltfish.example.config.ConfigBean;

import java.io.InputStream;

public class NettyServer extends Thread{
    private int port;

    public NettyServer(){
        try{
        ConfigBean config = ConfigBean.getInstance();
        port =(Integer)config.getVal("server-port");
        System.out.println(port);
        }catch (Exception o){
            System.err.println("错误：可能配置文件有误");
            o.printStackTrace();
        }
    }

    @Override
    public void run() {

        //主从多线程模式
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootStrap = new ServerBootstrap();

        try {

            bootStrap.group(bossGroup, workerGroup)                          //设置2个线程组
                    .channel(NioServerSocketChannel.class)                 //使用NioServerSocketChannel作为服务器的通道
                    .option(ChannelOption.SO_BACKLOG, 128)            //设置线程等待的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    .childHandler(new ServersInitializer());
            ChannelFuture channelFuture = bootStrap.bind(8888).sync();

            channelFuture.channel().closeFuture().sync();



        }catch (Exception e){
            e.printStackTrace();
        }





    }
}
