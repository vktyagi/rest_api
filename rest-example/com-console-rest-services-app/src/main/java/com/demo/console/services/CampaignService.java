package com.demo.console.services;

import java.util.List;
import java.util.Map;

import com.demo.console.models.entities.Campaign;
import com.demo.console.models.json.CampaignJson;

public interface CampaignService {

    CampaignJson getCampaignById(Long campaignId);

    CampaignJson createTvCampaign(CampaignJson ampTvCampaign, String userName);

    CampaignJson converToJson(Campaign campaign);
 }