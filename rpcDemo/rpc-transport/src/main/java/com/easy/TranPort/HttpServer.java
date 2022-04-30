package com.easy.TranPort;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HttpServer implements TransServer{
    private RequsetHandler handler;
    private Server server;
    @Override
    public void init(int port, RequsetHandler handler) {
        this.handler = handler;
        this.server = new Server(port);

        ServletContextHandler ctx = new ServletContextHandler();
        this.server.setHandler(ctx);

        ServletHolder hold = new ServletHolder(new RequestServlet());
        ctx.addServlet(hold,"/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    class RequestServlet extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            InputStream in = req.getInputStream();
            OutputStream out = resp.getOutputStream();
            if(handler != null){
                handler.onRequest(in,out);
            }
            out.flush();
        }
    }
}
