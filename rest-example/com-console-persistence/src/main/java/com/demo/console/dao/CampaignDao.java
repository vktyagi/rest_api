package com.demo.console.dao;

import java.util.List;

import com.demo.console.models.entities.Campaign;

public interface CampaignDao extends BaseDao<Campaign> {

    Campaign findCampaignByName(String campaignName);

    
    @Override
    Campaign save(Campaign campaign);

    Campaign getCampaignById(Long id);

    List<Campaign> getCampaignByStatus(String status, Integer offset, Integer limit, String sortOn, String order);

}
