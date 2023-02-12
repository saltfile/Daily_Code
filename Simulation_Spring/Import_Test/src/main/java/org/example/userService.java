package org.example;

import org.example.saltfish.annotations.Autowired;
import org.example.saltfish.annotations.Component;
import org.example.saltfish.annotations.Scope;
import org.example.saltfish.package_scan.BeanAware;

@Component
//@Scope("prototype")
public class userService implements BeanAware {

    @Autowired
    private testService testService;

    private String BeanName;
    public void xxx(){
        System.out.println(testService);
    }

    @Override
    public void setBeanName(String beanName) {
        this.BeanName = beanName;
    }
}
