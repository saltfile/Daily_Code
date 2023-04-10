package org.example.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.omg.CORBA.Environment;

import java.io.FileInputStream;

public class PulishHandler extends SimpleChannelInboundHandler<HttpObject> {



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest) {
            String url = ((HttpRequest) httpObject).uri();
            if (url.matches("/ufile/(.*)")) {
                System.out.println("msg的真实类型" + httpObject.getClass());
                System.out.println("客服端地址" + channelHandlerContext.channel().remoteAddress());
                String base = "/opt/git_Pro/Daily_Code/netty_tomcat/Files";

                String[] strs = url.split("/");
                String file_path = base+"/"+strs[strs.length-1];
                System.out.println(file_path);
                FileInputStream inputStream = new FileInputStream(file_path);


                ByteBuf buffer = Unpooled.buffer();
                int code=0;
                while ((code=inputStream.read())!=-1){
                    buffer.writeByte(code);
                }
                inputStream.close();

                // 构造一个http响应体，即HttpResponse
                DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buffer);
                // 设置响应头信息
//                defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html");
//                defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
                // 将响应体写入到通道中
                channelHandlerContext.writeAndFlush(defaultFullHttpResponse);
                channelHandlerContext.close();
            }
    }
}}
