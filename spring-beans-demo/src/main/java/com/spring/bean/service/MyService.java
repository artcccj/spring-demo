package com.spring.bean.service;

import org.springframework.beans.factory.annotation.Autowired;

public class MyService {

  @Autowired
  private MyServiceV2 myService;

  public void serve() {
    System.out.println("MyService.serve");
  }

  public void init() {
    System.out.println("MyService.init");
  }
}
