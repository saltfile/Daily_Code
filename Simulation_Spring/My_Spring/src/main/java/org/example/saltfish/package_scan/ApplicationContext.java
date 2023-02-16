package org.example.saltfish.package_scan;

import org.example.saltfish.annotations.*;
import org.example.saltfish.aop.Advice;
import org.example.saltfish.aop.AopInvokeHandler;
import org.example.saltfish.aop.Aspect;
import org.example.saltfish.aop.PointCut;
import org.example.saltfish.bean_storage.BeanDefinition;
import org.example.saltfish.custom_Exception.BeanNotFoundException;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private Class config;


    private ConcurrentHashMap<String,BeanDefinition> beanDefMap = new ConcurrentHashMap<String, BeanDefinition>();

    private ConcurrentHashMap<String,Object> SingletonPool = new ConcurrentHashMap<String, Object>();

    private ArrayList<BeanProcessor> ProcessorList = new ArrayList<BeanProcessor>();

    private ConcurrentHashMap<String,Aspect> aspectPool = new ConcurrentHashMap<String, Aspect>();

//    private ConcurrentHashMap<String, Aspect> aspects = new ConcurrentHashMap<String, Aspect>();

   public   ApplicationContext(Class config){
        this.config = config;


       /**
        *  临时函数加载模拟切面
        */
//       PointCut pointCut = new PointCut("org.example",".*Service");
//       Advice advice = new AopTest();
//       aspect = new Aspect(advice,pointCut);



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



                           if (nclazz.isAnnotationPresent(Aspects.class)){
                               Object Aops = nclazz.getConstructor().newInstance();
                               if (Aops instanceof Advice){

                                   for (Method m : nclazz.getMethods()) {
                                       if (m.isAnnotationPresent(Pointcut.class)){
                                           Pointcut annota = m.getAnnotation(Pointcut.class);

                                           String cl = annota.ClassPattern();
                                           String ms = annota.methodPattern();

                                           PointCut pointCut = new PointCut(cl,ms);
                                           Advice advice = (Advice) Aops;

                                           Aspect as = new Aspect(advice,pointCut);

                                           aspectPool.put(pointCut.getMethodPattern(),as);

                                       }
                                   }
                               }

                               continue;
                           }




                           //查看是否有BeanProcess实现
                           if (BeanProcessor.class.isAssignableFrom(nclazz)){
                               BeanProcessor processor = (BeanProcessor) nclazz.newInstance();
                               ProcessorList.add(processor);
                           }

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


                   } catch (Exception e) {
                       e.printStackTrace();
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




    private Object AopProxyEnhance(Object bean){
//       if (bean.getClass().getName() == )
        if (bean.getClass().getMethods().length < 10)return bean;

        for (String s : aspectPool.keySet()) {
            if (bean.getClass().getName().matches(s)){
                bean = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                        bean.getClass().getInterfaces(),new AopInvokeHandler(bean,aspectPool.get(s)));
            }
        }

       return bean;

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


           //前&后置初始化
           for (BeanProcessor processor : ProcessorList) {
               processor.postProcessBeforeInstantiation(beanName,o);
           }

           // spring初始化执行InitializingBean
           if (o instanceof InitializingBean){
               ((InitializingBean) o).afterPropertiesSet();
           }

           //前&后置初始化
           for (BeanProcessor processor : ProcessorList) {
               processor.postProcessAfterInstantiation(beanName,o);
           }





           //@PostConstruct 项目启动的时候执行此注解作用的方法，用来初始化全局参数

           for (Method method : o.getClass().getMethods()) {
               if (method.isAnnotationPresent(PostConstruct.class)){
                   method.invoke(o);
               }
           }




           //AOP



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
                   bean = AopProxyEnhance(bean);
                   return bean;

               }
           }
    }






}
