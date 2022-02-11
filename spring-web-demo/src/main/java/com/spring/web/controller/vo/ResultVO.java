package com.spring.web.controller.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultVO {

  private Object data;

  private String errorCode;

  private String errorMsg;

}
