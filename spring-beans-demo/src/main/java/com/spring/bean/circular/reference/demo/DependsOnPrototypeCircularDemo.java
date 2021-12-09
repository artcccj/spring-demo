package com.spring.bean.circular.reference.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class DependsOnPrototypeCircularDemo {

  public static void main(String[] args) {
    final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    final AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
    beanPostProcessor.setBeanFactory(beanFactory);
    beanFactory.addBeanPostProcessor(beanPostProcessor);

    GenericBeanDefinition definition = new GenericBeanDefinition();
    definition.setBeanClass(ServiceA.class);
    definition.setDependsOn("serviceB"); // 显示指定依赖serviceB
    beanFactory.registerBeanDefinition("serviceA", definition);

    definition = new GenericBeanDefinition();
    definition.setBeanClass(ServiceB.class);
    definition.setDependsOn("serviceA"); // 显示指定依赖serviceA
    beanFactory.registerBeanDefinition("serviceB", definition);

    ServiceA serviceA = (ServiceA) beanFactory.getBean("serviceA");
    serviceA.serve();
    ServiceB serviceB = (ServiceB) beanFactory.getBean("serviceB");
    serviceB.serve();

  }

  public static class ServiceA {

    @Autowired
    private ServiceB serviceB;

    public void serve() {
      System.out.println("serviceB = " + serviceB);
    }
  }

  public static class ServiceB {

    @Autowired
    private ServiceA serviceA;

    public void serve() {
      System.out.println("serviceA = " + serviceA);
    }
  }

}
