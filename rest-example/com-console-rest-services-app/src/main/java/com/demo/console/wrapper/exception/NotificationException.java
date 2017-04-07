package com.demo.console.wrapper.exception;

public class NotificationException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public NotificationException() {
    super("Error processing Notification");
  }

  public NotificationException(String message) {
    super(message);
  }
}
