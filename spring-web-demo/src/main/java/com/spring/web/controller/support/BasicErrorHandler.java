package com.spring.web.controller.support;

import com.alibaba.fastjson.JSON;
import com.spring.web.controller.vo.ResultVO;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 覆盖所有找不到 mappingHandler 的场景
 */
@Slf4j
@Controller
public class BasicErrorHandler implements ErrorController {

  private ErrorAttributes errorAttributes;

  public BasicErrorHandler(ErrorAttributes errorAttributes) {
    this.errorAttributes = errorAttributes;
  }

  @Override
  public String getErrorPath() {
    return "error/error";
  }

  @ResponseBody
  @RequestMapping(value = "/error")
  public ResultVO handleError(HttpServletRequest request, HttpServletResponse response) {
    Map<String, Object> errorAttributes = this.errorAttributes.getErrorAttributes(new ServletWebRequest(request), true);
    Object trace = errorAttributes.remove("trace");
    log.error("未知异常:\n{}\n{}", JSON.toJSONString(errorAttributes, true), trace);
    ResultVO result = new ResultVO()
        .setErrorCode(response.getStatus() + "")
        .setErrorMsg(extractErrorMsg(errorAttributes));
    response.setStatus(HttpStatus.OK.value());
    return result;
  }

  private static String extractErrorMsg(Map<String, Object> errorAttributes) {

    Object message = errorAttributes.get("message");
    if (message != null) {
      return message.toString();
    }

    Object error = errorAttributes.get("error");
    if (error != null) {
      return error.toString();
    }

    return "Unknown Error!!!";
  }
}
