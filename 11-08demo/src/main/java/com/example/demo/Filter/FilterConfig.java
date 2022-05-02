package com.example.demo.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilterConfig implements WebMvcConfigurer {

    @Bean
    public FilterConfig getMyWebMvcConfig(){
       FilterConfig myWebMvcConfig = new FilterConfig() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("alogins");
                registry.addViewController("/logins").setViewName("alogin");
                registry.addViewController("/home_page.html").setViewName("longs");
                registry.addViewController("/test").setViewName("table");
            }
            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LogFilter()).addPathPatterns("/**")
                        .excludePathPatterns("/logins","/","/lon");
            }
        };
        return myWebMvcConfig;
    }




}
