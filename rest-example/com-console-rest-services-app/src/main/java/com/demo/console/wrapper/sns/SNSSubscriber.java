package com.demo.console.wrapper.sns;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.amazonaws.regions.Region;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.SubscribeRequest;

@Service
public class SNSSubscriber {

  private static final Logger LOGGER = Logger.getLogger(SNSSubscriber.class);

  @Resource
  private AmazonSNSClient snsClient;

  @Resource
  String topicArn;

  @Resource
  String snsEndpoint;

  @PostConstruct
  public void init() {
    snsClient.setRegion(Region.getRegion(com.amazonaws.regions.Regions.US_EAST_1));
    LOGGER.debug("sns client initialized");
  }

  public void subscribe() {
    // subscribe to an SNS topic
    LOGGER.info("Subscribing sns endpoint " + snsEndpoint + " with " + topicArn);
    SubscribeRequest subRequest = new SubscribeRequest(topicArn, "http", snsEndpoint);
    snsClient.subscribe(subRequest);
    // get request id for SubscribeRequest from SNS metadata
    LOGGER.debug("SubscribeRequest - " + snsClient.getCachedResponseMetadata(subRequest));
  }
}
