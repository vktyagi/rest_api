package com.demo.console.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.demo.console.dao.RunDateDao;
import com.demo.console.models.entities.RunDate;
import com.demo.console.models.entities.Campaign;

@Repository("runDateDao")
public class RunDateDaoImpl extends BaseDaoImpl<RunDate> implements RunDateDao {

  public RunDateDaoImpl() {
    super(RunDate.class);
  }

  @Override
  public List<RunDate> findByCampaign(Campaign campaign) {

    String query = "from RunDate where campaign = :campaign";

    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("campaign", campaign);

    return findListByNameParameters(query, queryParams);
  }

  @Override
  public int deleteRunDate(Campaign campaignsInfo) {

    String query = "DELETE from RunDate where campaign =:campaignsInfo";
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("campaignsInfo", campaignsInfo);
    return updateOrDeleteByQueryNamedParams(query, queryParams);

  }

}
