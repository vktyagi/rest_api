package com.demo.console.wrapper.exception;

public class BadRequestParameterException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public BadRequestParameterException() {
    super("Bad request parameter.");
  }

  public BadRequestParameterException(String message) {
    super(message);
  }

}
