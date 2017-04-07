package com.demo.console.wrapper.exception;

public class DataParserException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DataParserException() {
    super("Error processing JSON ");
  }

  public DataParserException(String message) {
    super(message);
  }
}
