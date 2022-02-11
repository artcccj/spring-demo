package com.spring.redis;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RedisServiceTest {

  @Autowired
  private RedisService redisService;

  @Test
  void setNx() {
    for (int i = 0; i < 10; i++) {
      redisService.setNx("N1", "2" + i);
    }
  }

  @Test
  void setEx() {
    for (int i = 0; i < 10; i++) {
      redisService.setEx("E1", 10, "2" + i);
    }
  }

  @Test
  void set() {
    for (int i = 0; i < 10; i++) {
      redisService.set("1", "2" + i);
    }
  }
}