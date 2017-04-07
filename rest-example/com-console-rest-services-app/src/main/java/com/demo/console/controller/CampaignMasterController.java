package com.demo.console.controller;

import static com.demo.console.utils.ApplicationConstants.JSON_ATTR_CAMPAIGN_INFO;
import static com.demo.console.utils.ApplicationConstants.MSG_INVALID_CAMPAIGN_ID;
import static com.demo.console.utils.ApplicationConstants.REQUEST_DATE_FORMAT;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.console.messaging.MetaInfo;
import com.demo.console.models.json.CampaignJson;
import com.demo.console.services.CampaignService;
import com.demo.console.utils.ValidationUtils;
import com.demo.console.wrapper.exception.BadRequestParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping(value = "/campaigninfo", produces = "application/json;charset=UTF-8")
public class CampaignMasterController {
  private static final Logger LOGGER = Logger.getLogger(CampaignMasterController.class);
  @Autowired
  private CampaignService campaignService;

  @RequestMapping(value = { "", "/*", "/*/*", "/*/*/*", "/*/*/*/*" }, method = RequestMethod.OPTIONS)
  public String optionsDefault() {
    return "Server OK";

  }

  @RequestMapping(value = "campaignid/{campaignId}", method = RequestMethod.GET)
  public String getCampaign(@PathVariable(value = "campaignId") String campaignId) {

    LOGGER.info("Getting Campaign Info for :::" + campaignId);

    Gson gson = new GsonBuilder().setDateFormat(REQUEST_DATE_FORMAT).create();

    Map<String, Object> campainInfoResponse = new HashMap<>();

    Map<String, Object> metaInfo = new HashMap<String, Object>();
    Long cid = 0L;
    if (ValidationUtils.isNumeric(campaignId)) {
      cid = Long.parseLong(campaignId);
    } else {
      throw new BadRequestParameterException(MSG_INVALID_CAMPAIGN_ID + campaignId);
    }

    CampaignJson campaignsInfo = campaignService.getCampaignById(cid);
    campainInfoResponse.put("meta", new MetaInfo("/campaigninfo/campaignid/" + campaignId, "GET",
        metaInfo));
    campainInfoResponse.put(JSON_ATTR_CAMPAIGN_INFO, campaignsInfo);

    return gson.toJson(campainInfoResponse);
  }

  @RequestMapping(method = RequestMethod.POST)
  public String createCampaign(HttpServletRequest request, @RequestBody CampaignJson ampTvCampaign) {

    LOGGER.info("Saving Campaign Info for :::" + ampTvCampaign);

    Gson gson = new GsonBuilder().setDateFormat(REQUEST_DATE_FORMAT).create();

    String userName = (String) request.getAttribute("userName");

    Map<String, Object> metaInfo = new HashMap<String, Object>();

    Map<String, Object> campainInfoResponse = new LinkedHashMap<String, Object>();
    metaInfo.put(JSON_ATTR_CAMPAIGN_INFO, ampTvCampaign);
    campainInfoResponse.put("meta", new MetaInfo("/campaigninfo", "POST", metaInfo));
    campainInfoResponse.put("campaignId", campaignService.createTvCampaign(ampTvCampaign, userName)
        .getCampaignId());
    return gson.toJson(campainInfoResponse);
  }




}
