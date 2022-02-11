package com.spring.web.controller.support;

import com.spring.web.controller.vo.ResultVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 覆盖所有能找到 mappingHandler 的场景
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler(IllegalArgumentException.class)
  public ResultVO handleException(IllegalArgumentException e, HttpServletResponse response, HttpServletRequest request) {
    log.error("未知异常: {}", request.getRequestURL(), e);
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    return new ResultVO().setErrorCode("UNKNOWN").setErrorMsg("未知异常");
  }

}
