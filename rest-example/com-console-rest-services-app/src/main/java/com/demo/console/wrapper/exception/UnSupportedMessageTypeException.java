package com.demo.console.wrapper.exception;

public class UnSupportedMessageTypeException extends Exception {
  /**
     * 
     */
  private static final long serialVersionUID = 1L;

  public UnSupportedMessageTypeException() {
    super("Error processing MessageType");
  }

  public UnSupportedMessageTypeException(String message) {
    super(message);
  }
}
