package org.example.Initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.example.handler.PulishHandler;

public class PubilshInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("HttpResponseEncoder",new HttpResponseEncoder());
        pipeline.addLast("HttpRequestDecoder",new HttpRequestDecoder());

        pipeline.addLast("PuilshHandler",new PulishHandler());

    }
}
