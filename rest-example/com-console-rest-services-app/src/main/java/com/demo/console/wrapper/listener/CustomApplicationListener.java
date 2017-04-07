package com.demo.console.wrapper.listener;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.demo.console.wrapper.search.service.IndexingService;
import com.demo.console.wrapper.sns.SNSSubscriber;

@Component
public class CustomApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

  private static final Logger LOGGER = Logger.getLogger(SNSSubscriber.class);
  @Resource
  private SNSSubscriber snsSubscriber;

  @Resource(name = "indexingService")
  private IndexingService indexingService;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    // do whatever you need to do here when app context is initialized /
    // refreshed
    try {
      LOGGER.info("Subscribing sns endpoint after context refresh");
      snsSubscriber.subscribe();
    } catch (Exception e) {
      LOGGER.error("Error subscribing sns end point", e);
    }

    try {
      indexingService.initiateIndexing();
    } catch (Exception e) {
      LOGGER.error("Error while creating indexing", e);
    }
  }
}
