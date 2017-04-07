package com.demo.console.wrapper.exception;

import java.util.List;

public class FileFormatException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final String error;
  private final transient List<Object> errorValues;

  public FileFormatException(String message, String error, List<Object> errorValues) {
    super(message);
    this.error = error;
    this.errorValues = errorValues;
  }

  public String getError() {
    return error;
  }

  public List<Object> getErrorValues() {
    return errorValues;
  }

}
