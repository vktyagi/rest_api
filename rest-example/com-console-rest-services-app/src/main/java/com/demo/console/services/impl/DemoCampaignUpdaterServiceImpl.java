package com.demo.console.services.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.console.dao.CampaignDao;
import com.demo.console.dao.DemoCampaignDao;
import com.demo.console.dao.RunDateDao;
import com.demo.console.models.entities.Campaign;
import com.demo.console.models.entities.DemoCampaign;
import com.demo.console.models.entities.RunDate;
import com.demo.console.services.DemoCampaignUpdaterService;

@Service("demoCampaignUpdaterService")
public class DemoCampaignUpdaterServiceImpl implements DemoCampaignUpdaterService {
  private static final Logger LOGGER = Logger.getLogger(DemoCampaignUpdaterServiceImpl.class);

  @Autowired
  private CampaignDao campaignDao;

  @Autowired
  private DemoCampaignDao demoCampaignDao;

  @Autowired
  private RunDateDao runDateDao;

  @Override
  public void updateDemoCampaign() {

    List<DemoCampaign> demoCampaigns = demoCampaignDao.findAll();
    if (null == demoCampaigns || demoCampaigns.isEmpty()) {
      LOGGER.error("No demo Campaign available to update");
      return;
    }
    for (DemoCampaign demoCampaign : demoCampaigns) {

      boolean isContinue = false;

      Campaign campaign = demoCampaign.getCampaign();
      if (null == campaign) {
        LOGGER.info("No campaign found for demo id : " + demoCampaign.getId());
        isContinue = true;
      }

      List<RunDate> runDates = runDateDao.findByCampaign(campaign);
      if (null == runDates || runDates.isEmpty()) {
        LOGGER.info("Run dates not found for campaign : " + campaign.getCampaignId());
        isContinue = true;
      }

      if (isContinue) {
        continue;
      }

      LOGGER.info("Updating campaign :" + campaign.getCampaignId());
      Date lastCampaignUpdatedDate = demoCampaign.getUpdatedDate();
      LOGGER.info("Last Updated date of campaign : " + lastCampaignUpdatedDate);

      Date currDate = new Date();
      LOGGER.info("Current Date : " + currDate);
      int updateDayDiff = getDaysBetween(currDate, lastCampaignUpdatedDate);
      LOGGER.info("Day difference : " + updateDayDiff);

      if (updateDayDiff > 0) {
        for (RunDate runDate : runDates) {
          LOGGER.info("Info for Run date : " + runDate.getRunDateId());
          Date lastStartRunDate = runDate.getStartDate();
          LOGGER.info("Last start date of run date : " + lastStartRunDate);
          Date lastEndRunDate = runDate.getEndDate();
          LOGGER.info("Last end date of run date : " + lastEndRunDate);

          Date newStartRunDate = getIncrementedDate(lastStartRunDate, updateDayDiff);
          Date newEndRunDate = getIncrementedDate(lastEndRunDate, updateDayDiff);
          LOGGER.info("New start date : " + newStartRunDate);
          LOGGER.info("New end date : " + newEndRunDate);

          runDate.setStartDate(newStartRunDate);
          runDate.setEndDate(newEndRunDate);
          runDate.setUpdatedDate(new Timestamp(currDate.getTime()));

          runDateDao.update(runDate);

        }
        LOGGER.info("Updating campaign date ");
        demoCampaign.setUpdatedDate(new Timestamp(currDate.getTime()));
        campaign.setUpdatedDate(new Timestamp(currDate.getTime()));
        demoCampaignDao.update(demoCampaign);
        campaignDao.update(campaign);
        LOGGER.info("Campaign updated successfully : " + campaign.getCampaignId());
      }
    }

  }

  private Date getIncrementedDate(Date currDate, int addDay) {
    Calendar c = Calendar.getInstance();
    c.setTime(currDate);
    c.add(Calendar.DATE, addDay);
    return c.getTime();
  }

  private int getDaysBetween(Date currDate, Date lastDate) {
    return Days.daysBetween(new LocalDate(lastDate.getTime()), new LocalDate(currDate.getTime()))
        .getDays();
  }

}
