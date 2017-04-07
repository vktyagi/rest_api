package com.demo.console.wrapper.exception;

public class NoDataFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public NoDataFoundException() {
    super("Error processing data");
  }

  public NoDataFoundException(String message) {
    super(message);
  }
}
