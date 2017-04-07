package com.demo.console.test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.demo.console.dao.CampaignDao;
import com.demo.console.models.entities.Campaign;

public class CampaignPersistenceTest extends BaseTestCase {

  @Resource
  CampaignDao campaignDao;

  @Test
  public void testCampaignCRUD() {

    testCreate();
    testFind();
    testUpdate();
  }

  // @Test()
  public void testCreate() {

    Campaign ci = new Campaign();
    // ci.setCampaignId(123L);
    ci.setAdvertiserId("Test Advertiser");
    ci.setCampaignName("Test Campaign Name");
    ci.setBudget(BigDecimal.valueOf(0.001));
    ci.setCpmGoal(new BigDecimal(99999999999.99));
    ci.setUpdatedBy("Test System");
    ci.setUpdatedDate(new Timestamp(new Date().getTime()));

    try {
      Assert.assertTrue("Campaign ID generated", campaignDao.create(ci).getCampaignId() > 0);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // @Test
  public void testFind() {
    List<Campaign> cis = campaignDao.findAll();

    Assert.assertTrue("No CampaignInfo found.", cis.size() != 0);

    Assert.assertTrue("Test Campaign not found",
        cis.get(0).getCampaignName().equals("Test Campaign Name"));
  }

  // @Test
  public void testUpdate() {
    List<Campaign> cis = campaignDao.findAll();

    Assert.assertTrue("No CampaignInfo found.", cis.size() != 0);

    Long id = cis.get(0).getCampaignId();
    Campaign ci = campaignDao.findExactlyOne((long) id);

    ci.setAdvertiserId("adid");
    campaignDao.update(ci);

    Campaign changedCI = campaignDao.findExactlyOne((long) id);
    Assert.assertTrue("Campaign Info not updated", changedCI.getAdvertiserId().equals("adid"));

  }
}
