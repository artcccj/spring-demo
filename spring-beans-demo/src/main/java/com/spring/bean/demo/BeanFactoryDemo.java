package com.spring.bean.demo;

import com.spring.bean.service.MyService;
import com.spring.bean.service.MyServiceV2;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class BeanFactoryDemo {

  public static void main(String[] args) {
    final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    final AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
    beanPostProcessor.setBeanFactory(beanFactory);
    beanFactory.addBeanPostProcessor(beanPostProcessor);

    GenericBeanDefinition definition = new GenericBeanDefinition();
//    definition.setBeanClass(MyService.class);
//    definition.setInitMethodName("init");
////    definition.setDependsOn("testV2"); // 显示DependsOn多实例Bean
////    definition.setScope(BeanDefinition.SCOPE_PROTOTYPE); // 都是多实例的Bean
//    beanFactory.registerBeanDefinition("test", definition);

//    definition = new GenericBeanDefinition();
    definition.setBeanClass(MyServiceV2.class);
//    definition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
    definition.setDependsOn("testV2"); // 显示指定DependsOn 自己
//    definition.setDependsOn("test");
    beanFactory.registerBeanDefinition("testV2", definition);

    final Object test = beanFactory.getBean("testV2");
    ((MyService) test).serve();
    System.out.println(test.getClass().getName());
  }

}
