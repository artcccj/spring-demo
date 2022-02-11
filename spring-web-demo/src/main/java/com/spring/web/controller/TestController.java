package com.spring.web.controller;

import com.spring.web.controller.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

  @GetMapping("/username/get")
  public ResultVO getUsername() {
    return new ResultVO().setData("shiwen.chen");
  }

  @GetMapping("/exception/get")
  public ResultVO getException() {
    throw new IllegalStateException("测试异常");
  }

}
