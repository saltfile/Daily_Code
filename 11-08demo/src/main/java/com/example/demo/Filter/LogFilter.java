package com.example.demo.Filter;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
@Component
public class LogFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            System.out.println("Controller :" + ((HandlerMethod) handler).getBean().getClass().getName() + "被请求");
            System.out.println("Controller 功能:" + ((HandlerMethod) handler).getMethod().getName() + "被使用");
            request.setAttribute("startTime：", new Date().getTime());
        }catch (Exception e){

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(request.getAttribute("startTime") != null) {
            long start = (long) request.getAttribute("startTime");

            System.out.println("TimeInterceptor 处理时长为：" + (new Date().getTime() - start));
        }else {
            request.setAttribute("startTime：", new Date().getTime());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LogFilter 完成 Controller"+ request.getRequestURI());
        long start = (long) request.getAttribute("startTime：");
        System.out.println("LogFilter 处理时长为："+ (new Date().getTime() - start));
    }
}
