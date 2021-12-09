package com.spring.bean.circular.reference.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class SelfCircularDemo {

  public static void main(String[] args) {
    final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    final AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
    beanPostProcessor.setBeanFactory(beanFactory);
    beanFactory.addBeanPostProcessor(beanPostProcessor);

    GenericBeanDefinition definition = new GenericBeanDefinition();
    definition.setBeanClass(ServiceA.class);
    definition.setDependsOn("serviceA");
    beanFactory.registerBeanDefinition("serviceA", definition);

    ServiceA serviceA = (ServiceA) beanFactory.getBean("serviceA");
    serviceA.serve();
  }

  public static class ServiceA {

    @Autowired
    private ServiceA serviceB;

    public void serve() {
      System.out.println("serviceB = " + serviceB);
    }
  }

}
