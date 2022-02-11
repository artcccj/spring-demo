package com.spring.redis;

import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisService {

  private final StringRedisTemplate stringRedisTemplate;

  public void setNx(String key, String value) {
    stringRedisTemplate.execute((RedisCallback<String>) connection -> {
      Boolean ret = connection.setNX(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
      log.warn("redis setNX result : {}", ret);
      return null;
    });
  }

  public void setEx(String key, long seconds, String value) {
    stringRedisTemplate.execute((RedisCallback<String>) connection -> {
      Boolean ret = connection.setEx(key.getBytes(StandardCharsets.UTF_8), seconds, value.getBytes(StandardCharsets.UTF_8));
      log.warn("redis setEx result : {}", ret);
      return null;
    });
  }

  public void set(String key, String value) {
    stringRedisTemplate.execute((RedisCallback<String>) connection -> {
      Boolean ret = connection.set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
      log.warn("redis set result : {}", ret);
      return null;
    });
  }

}
