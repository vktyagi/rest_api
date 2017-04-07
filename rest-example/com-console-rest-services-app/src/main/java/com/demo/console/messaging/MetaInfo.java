package com.demo.console.messaging;

import java.util.Map;

public class MetaInfo {
  private String action;
  private String methodType;
  private Map<String, Object> requestAttribute;

  public MetaInfo() {
  }

  public MetaInfo(String action, String methodType, Map<String, Object> requestAttribute) {
    this.action = action;
    this.methodType = methodType;
    this.requestAttribute = requestAttribute;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String getMethodType() {
    return methodType;
  }

  public void setMethodType(String methodType) {
    this.methodType = methodType;
  }

  public Map<String, Object> getRequestAttribute() {
    return requestAttribute;
  }

  public void setRequestAttribute(Map<String, Object> requestAttribute) {
    this.requestAttribute = requestAttribute;
  }

}
