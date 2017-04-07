package com.demo.console.jobs;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.console.services.DemoCampaignUpdaterService;

public class DemoCampaignUpdateScheduler {
  private static final Logger LOGGER = Logger.getLogger(DemoCampaignUpdateScheduler.class);

  @Autowired
  DemoCampaignUpdaterService demoCampaignUpdaterService;

  public void runJobForUpdateDemoCampaign() {
    LOGGER.info("Starting Demo campaigns update scheduler.." + new Date());
    demoCampaignUpdaterService.updateDemoCampaign();
    LOGGER.info("Ended Demo campaigns update scheduler..");
  }
}
