package org.example.saltfish.package_scan;

import org.example.saltfish.annotations.Autowired;
import org.example.saltfish.annotations.Component;
import org.example.saltfish.annotations.ConmponetScan;
import org.example.saltfish.annotations.Scope;
import org.example.saltfish.bean_storage.BeanDefinition;
import org.example.saltfish.custom_Exception.BeanNotFoundException;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private Class config;


    private ConcurrentHashMap<String,BeanDefinition> beanDefMap = new ConcurrentHashMap<String, BeanDefinition>();

    private ConcurrentHashMap<String,Object> SingletonPool = new ConcurrentHashMap<String, Object>();
   public   ApplicationContext(Class config){
        this.config = config;
       //启动时加载
        //包扫描过程:如果配置类上面有该注解就要去扫描这个类的所有包
       if (config.isAnnotationPresent(ConmponetScan.class)) {
           ConmponetScan annotation = (ConmponetScan) config.getAnnotation(ConmponetScan.class);
           //包路径
           String val = annotation.path();
           System.out.println(val);
           String path = val.replace(".","/");
           ClassLoader classLoader = ApplicationContext.class.getClassLoader();
           //找到需要扫描的包路径
           URL url = classLoader.getResource(path);

           File dir = new File(url.getFile());

           //检测这个包是不是个文件夹
           if (dir.isDirectory()){
               File[] files = dir.listFiles();

               for (File f:files){
                   try {

                   String absolutePath = f.getAbsolutePath();
                   //合并包路径
                   String packeName = val+"."+f.getName().substring(0,f.getName().indexOf(".class"));

                   if (absolutePath.endsWith(".class")){
                       Class<?> nclazz = classLoader.loadClass(packeName);

                       if (nclazz.isAnnotationPresent(Component.class)) {
                           //如果说这个上面有component就说明是Bean

                           Component bean = nclazz.getAnnotation(Component.class);
                           String beanName = bean.value();

                           if (beanName.equals("")){
//                               String[] names = nclazz.getName().split("\\.");
//                               beanName = names[names.length-1];
                               //改进

                               beanName = Introspector.decapitalize(nclazz.getSimpleName());
                           }


                           BeanDefinition beanDefinition = new BeanDefinition();
                           beanDefinition.setType(nclazz);
                           // 看看是否是单例还是多例
                           if (nclazz.isAnnotationPresent(Scope.class)) {
                               Scope socpes = nclazz.getAnnotation(Scope.class);
                               beanDefinition.setScope(socpes.value());

                           }else {
                               //没有就默认单例
                               beanDefinition.setScope("singletion");
                           }

                           beanDefMap.put(beanName,beanDefinition);
                       }

                   }


                   } catch (ClassNotFoundException e) {
                       throw new RuntimeException(e);
                   }

               }
           }

       }

       //创建单例bean的池子
       for (String bName : beanDefMap.keySet()) {
           BeanDefinition beanDefinition = beanDefMap.get(bName);
           if (!beanDefinition.getScope().equals("prototype")){
               Object bean = createBean(bName,beanDefinition);

               SingletonPool.put(bName,bean);

           }

       }

    }


    //这里是建立单例bean的地方
    private Object createBean(String beanName,BeanDefinition definition){
       try {
           //首先反射创建type
           Class type = definition.getType();

           Object o = type.getConstructor().newInstance();


           //装配  依赖注入
           for (Field df : type.getDeclaredFields()) {
               if (df.isAnnotationPresent(Autowired.class)){
                   df.setAccessible(true);
                   df.set(o,getBean(df.getName()));
               }
           }

           //beanname回调

           if (o instanceof BeanAware){
               ((BeanAware) o).setBeanName(beanName);
           }




           return o;
       }catch (Exception r){
           r.printStackTrace();
       }
        return null;
    }



    public Object getBean(String beanName) throws BeanNotFoundException {


           BeanDefinition beanDefinition = beanDefMap.get(beanName);
           //如果没找到说明对应的bean没有被扫到或者不存在
           if (beanDefinition == null) {
               throw new BeanNotFoundException(beanName);
           }else {

               //首先确定类型
               String scope = beanDefinition.getScope();


               //多例
               if (scope.equals("prototype")){

                   return createBean(beanName,beanDefinition);

               }else {

                   Object bean = SingletonPool.get(beanName);
                   if (bean == null){
                       Object bsn = createBean(beanName,beanDefinition);
                       SingletonPool.put(beanName,bsn);
                       return bsn;
                   }
                   return bean;

               }
           }
    }






}
