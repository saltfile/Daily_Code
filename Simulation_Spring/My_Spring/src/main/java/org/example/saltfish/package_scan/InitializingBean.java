package org.example.saltfish.package_scan;

/**
 * InitializingBean是spring为bean的初始化提供了一种新的方式，里面只有一个方法afterPropertiesSet，
 * 作用就是实现这个接口或者实现了继承InitializingBean的方法的bean都要执行这个方法。
 */
public interface InitializingBean {
    public void afterPropertiesSet();
}
