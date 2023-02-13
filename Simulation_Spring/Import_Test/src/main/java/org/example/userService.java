package org.example;

import org.example.saltfish.annotations.Autowired;
import org.example.saltfish.annotations.Component;
import org.example.saltfish.annotations.PostConstruct;
import org.example.saltfish.annotations.Scope;
import org.example.saltfish.package_scan.BeanAware;
import org.example.saltfish.package_scan.InitializingBean;

@Component
//@Scope("prototype")
public class userService implements BeanAware, InitializingBean {

    @Autowired
    private testService testService;

    private String BeanName;

    public void xxx(){
        System.out.println(testService);
        System.out.println(BeanName);
    }
    @PostConstruct
    public void testPost(){
        System.out.println("hello world");
    }


    @Override
    public void setBeanName(String beanName) {
        this.BeanName = beanName;
    }


    @Override
    public void afterPropertiesSet() {

        System.out.println("我是初始化时候调用的");
        this.BeanName = "xxx";
    }
}
