package com.demo.console.wrapper.exception;

public class NoRankerResponseReceivedException extends RuntimeException {
  /**
     * 
     */
  private static final long serialVersionUID = 1L;

  public NoRankerResponseReceivedException() {
    super("Error receiving ranker response");
  }

  public NoRankerResponseReceivedException(String message) {
    super(message);
  }
}
