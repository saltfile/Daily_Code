package org.saltfish.example.Server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.servlet.http.HttpServletRequest;

public class ServletHandler extends SimpleChannelInboundHandler<HttpServletRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpServletRequest httpServletRequest) throws Exception {
        System.out.println(httpServletRequest.getParameter("host"));



    }
}
