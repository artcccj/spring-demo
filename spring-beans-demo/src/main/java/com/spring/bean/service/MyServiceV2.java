package com.spring.bean.service;

import org.springframework.beans.factory.annotation.Autowired;

public class MyServiceV2 {

  @Autowired
  private MyServiceV2 myService;

  public void serve() {
    System.out.println("MyServiceV2.serve");
  }

}
