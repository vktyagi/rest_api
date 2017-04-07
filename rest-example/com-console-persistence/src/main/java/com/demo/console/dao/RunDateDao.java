package com.demo.console.dao;

import java.util.List;

import com.demo.console.models.entities.RunDate;
import com.demo.console.models.entities.Campaign;

public interface RunDateDao extends BaseDao<RunDate> {

    List<RunDate> findByCampaign(Campaign campaign);

    public int deleteRunDate(Campaign campaignsInfo);

}
