package com.demo.console.services;

import java.util.Map;

import com.demo.console.messaging.NotificationMessage;
import com.demo.console.messaging.SubscriptionConfirmationMessage;
import com.demo.console.wrapper.exception.UnSupportedMessageTypeException;

public interface MessagingService {

  void subscribe(SubscriptionConfirmationMessage message);

  Map<String, Object> publish(Map<String, String> message);

  String errorPublish(Map<String, String> message);

  void notify(NotificationMessage message) throws UnSupportedMessageTypeException;
}
