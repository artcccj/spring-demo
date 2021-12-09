package com.spring.bean.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class PrototypeBeanCircularDemo {

  public static void main(String[] args) {
    final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    final AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
    beanPostProcessor.setBeanFactory(beanFactory);
    beanFactory.addBeanPostProcessor(beanPostProcessor);

    GenericBeanDefinition definition = new GenericBeanDefinition();
    definition.setBeanClass(ServiceA.class);
    definition.setScope(BeanDefinition.SCOPE_PROTOTYPE); // serviceA 声明为原型的
    beanFactory.registerBeanDefinition("serviceA", definition);

    definition = new GenericBeanDefinition();
    definition.setBeanClass(ServiceB.class);
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
