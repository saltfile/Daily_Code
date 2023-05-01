package org.saltfish.example.Server.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.SystemPropertyUtil;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.omg.CORBA.BAD_CONTEXT;
import org.saltfish.example.Server.task.MultipartRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpHandler;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;

public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    ExecutorService pool = new ThreadPoolExecutor(120,300,5, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        if (!fullHttpRequest.decoderResult().isSuccess()){
            SendError(channelHandlerContext, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        System.out.println(fullHttpRequest.headers().get("file"));

//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(fullHttpRequest.getSession().getServletContext());
//        commonsMultipartResolver.setDefaultEncoding("utf-8");
//        MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart((HttpServletRequest) fullHttpRequest);
//        MultipartFile fileddd = multipartRequest.getFile("file");
//


//        String ifModifiedSince = fullHttpRequest.headers().get(HttpHeaderNames.IF_MODIFIED_SINCE);
//
//
//        RandomAccessFile raf;
//        try {
//            raf = new RandomAccessFile(file, "r");
//        } catch (FileNotFoundException ignore) {
//            SendError(channelHandlerContext, NOT_FOUND);
//            return;
//        }
//        long fileLength = raf.length();


        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes("hello world".getBytes());
        // 构造一个http响应体，即HttpResponse
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buffer);
        // 设置响应头信息
//                defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html");
//                defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
        // 将响应体写入到通道中
        channelHandlerContext.writeAndFlush(defaultFullHttpResponse);
        channelHandlerContext.close();







    }


    private static MultipartRequest getMultipartBody(FullHttpRequest request) {
        try {
            //创建HTTP对象工厂
            HttpDataFactory factory = new DefaultHttpDataFactory(true);
            //使用HTTP POST解码器
            HttpPostRequestDecoder httpDecoder = new HttpPostRequestDecoder(factory, request);
            httpDecoder.setDiscardThreshold(0);
            if (httpDecoder != null) {
                //获取HTTP请求对象
                final HttpContent chunk = (HttpContent) request;
                //加载对象到加吗器。
                httpDecoder.offer(chunk);
                if (chunk instanceof LastHttpContent) {
                    //自定义对象bean
                    MultipartRequest multipartRequest = new MultipartRequest();
                    //存放文件对象
                    Map<String, FileUpload> fileUploads = new HashMap<>();
                    //存放参数对象
                    JSONObject body = new JSONObject();
                    //通过迭代器获取HTTP的内容
                    java.util.List<InterfaceHttpData> InterfaceHttpDataList = httpDecoder.getBodyHttpDatas();
                    for (InterfaceHttpData data : InterfaceHttpDataList) {
                        //如果数据类型为文件类型，则保存到fileUploads对象中
                        if (data != null && InterfaceHttpData.HttpDataType.FileUpload.equals(data.getHttpDataType())) {
                            FileUpload fileUpload = (FileUpload) data;
                            fileUploads.put(data.getName(), fileUpload);
                        }
                        //如果数据类型为参数类型，则保存到body对象中
                        if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                            Attribute attribute = (Attribute) data;
                            body.put(attribute.getName(), attribute.getValue());
                        }
                    }
                    //存放文件信息
                    multipartRequest.setFileUploads(fileUploads);
                    //存放参数信息
                    multipartRequest.setParams(body);
                    return multipartRequest;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String sanitizeUri(String uri) {
        // Decode the path.
        try {
            uri = URLDecoder.decode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new Error(e);
        }

        if (uri.isEmpty() || uri.charAt(0) != '/') {
            return null;
        }

        // Convert file separators.
        uri = uri.replace('/', File.separatorChar);

        // Simplistic dumb security check.
        // You will have to do something serious in the production environment.
        if (uri.contains(File.separator + '.') ||
                uri.contains('.' + File.separator) ||
                uri.charAt(0) == '.' || uri.charAt(uri.length() - 1) == '.' ) {
            return null;
        }

        // Convert to absolute path.
        return SystemPropertyUtil.get("user.dir") + File.separator + uri;
    }
    private static void SendError(ChannelHandlerContext ctx, HttpResponseStatus status){
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,status, Unpooled.copiedBuffer("Failure: " + status.toString()+ "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE,"text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }




}
