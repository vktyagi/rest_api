package com.demo.console.messaging;

import com.demo.console.models.json.RankerOutputRoot;

public class ApplicationMessage {

  String type;
  String resource;
  Long campaignId;
  RankerOutputRoot response;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public Long getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(Long campaignId) {
    this.campaignId = campaignId;
  }

  public RankerOutputRoot getResponse() {
    return response;
  }

  public void setResponse(RankerOutputRoot response) {
    this.response = response;
  }

}
