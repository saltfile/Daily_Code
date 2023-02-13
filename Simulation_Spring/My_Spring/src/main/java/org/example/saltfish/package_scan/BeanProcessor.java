package org.example.saltfish.package_scan;


/**
 * BeanProcessor是spring中的一个重要接口，他有两个接口方法一个是postProcessBeforeInitialization前置初始化，
 * 另一个是postProcessAfterInitialization后置初始化。
 */


public interface BeanProcessor{

    public void postProcessBeforeInstantiation(String beanName,Object bean);

    public void postProcessAfterInstantiation(String beanName,Object bean);

}
