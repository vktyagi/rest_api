package com.demo.console.dao.impl;

import org.springframework.stereotype.Repository;

import com.demo.console.dao.DemoCampaignDao;
import com.demo.console.models.entities.DemoCampaign;

@Repository("demoCampaignDao")
public class DemoCampaignDaoImpl extends BaseDaoImpl<DemoCampaign> implements DemoCampaignDao {

  public DemoCampaignDaoImpl() {
    super(DemoCampaign.class);
  }

}
