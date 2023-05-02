package org.saltfish.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.saltfish.example.Server.NettyServer;
import org.saltfish.example.Server.initializer.ServersInitializer;
import org.saltfish.example.Server.initializer.ServletInitializer;

public class ServerApplicaiton {
    public static void main(String[] args) {
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
