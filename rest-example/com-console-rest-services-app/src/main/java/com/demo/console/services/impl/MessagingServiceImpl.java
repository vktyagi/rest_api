package com.demo.console.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.amazonaws.regions.Region;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishResult;
import com.demo.console.messaging.ApplicationMessage;
import com.demo.console.messaging.ApplicationMessageType;
import com.demo.console.messaging.NotificationMessage;
import com.demo.console.messaging.SubscriptionConfirmationMessage;
import com.demo.console.services.MessagingService;
import com.demo.console.wrapper.exception.DataParserException;
import com.demo.console.wrapper.exception.NotificationException;
import com.demo.console.wrapper.exception.UnSupportedMessageTypeException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service("messagingService")
public class MessagingServiceImpl implements MessagingService {

  private static final Logger LOGGER = Logger.getLogger(MessagingServiceImpl.class);

  @Resource
  private AmazonSNSClient snsClient;

  @Resource
  String topicArn;

  @Resource
  String exptopicArn;

  @PostConstruct
  public void init() {
    snsClient.setRegion(Region.getRegion(com.amazonaws.regions.Regions.US_EAST_1));
  }

  @Override
  public void notify(NotificationMessage message) throws UnSupportedMessageTypeException {

    try {
      Gson gson = new Gson();
      ApplicationMessage aMessage = gson.fromJson(message.getMessage().toString(),
          ApplicationMessage.class);
      LOGGER.info("Application Message received from Notification message: " + aMessage);
      if (aMessage.getType().equals(ApplicationMessageType.BASE_PLAN.name())) {
        LOGGER.info(aMessage.getResource());
      } else if (aMessage.getType().equals(ApplicationMessageType.CAMPAIGN_PARAM.name())) {
        LOGGER.info(aMessage.getResource());
      } else {
        throw new UnSupportedMessageTypeException(aMessage.getType() + " is not a supported type");
      }
    } catch (Exception e) {
      LOGGER.error("Error occurs while parsig json. " + e.getMessage() + " : ", e);
      throw new DataParserException("Error occurs while parsing json");
    }
  }

  @Override
  public Map<String, Object> publish(Map<String, String> message) {
    Map<String, Object> msgMap = new HashMap<String, Object>();
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      PublishResult pr = snsClient.publish(topicArn, objectMapper.writeValueAsString(message));
      LOGGER.info(pr.getMessageId());
      msgMap.put("type", "SUCCESS");
      msgMap.put("messageId", pr.getMessageId());
    } catch (JsonProcessingException e) {
      LOGGER.error("Error occurs while parsing json." + e.getMessage() + " : ", e);
      msgMap.put("type", "ERROR");
      msgMap.put("message", "Error occurs while parsing json");
    }
    return msgMap;
  }

  @Override
  public String errorPublish(Map<String, String> message) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      PublishResult pr = snsClient.publish(exptopicArn, objectMapper.writeValueAsString(message));
      LOGGER.info(pr.getMessageId());
      return "SUCCESS: " + pr.getMessageId();
    } catch (JsonProcessingException e) {
      LOGGER.error("Error occurs while parsing json. " + e.getMessage() + " : ", e);
      return "ERROR: Error occurs while parsing json";
    }
  }

  @Override
  public void subscribe(SubscriptionConfirmationMessage message) {

    HttpClient httpClient = new HttpClient();
    HttpMethod httpGet = new GetMethod(message.getSubscribeURL());
    try {
      int httpResp = httpClient.executeMethod(httpGet);
      LOGGER.info("HTTP responsed received from subscription url: " + httpResp
          + "\nSubsription URL: " + message.getSubscribeURL());
    } catch (IOException e) {
      LOGGER.error("Error occurred in subscription confirmation. \nError: " + e.getMessage()
          + "\nSubsription URL: " + message.getSubscribeURL(), e);
      throw new NotificationException("Error occurred in subscription confirmation. \nError: "
          + e.getMessage() + "\nSubsription URL: " + message.getSubscribeURL());
    }
  }
}
