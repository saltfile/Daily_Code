package org.example.saltfish.package_scan;

/**
 * 通过Aware接口，可以对Spring相应资源进行操作（一定要慎重，因为获取的资源可能是IOC的核心资源）。
 * 为对Spring进行简单的扩展提供了方便的接口
 */
public interface BeanAware {
    public void setBeanName(String beanName);


}
