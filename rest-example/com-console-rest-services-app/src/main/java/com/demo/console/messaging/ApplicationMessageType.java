package com.demo.console.messaging;

public enum ApplicationMessageType {
  BASE_PLAN(1, "Base Plan", "api/v1/getbaseplan"), CAMPAIGN_PARAM(2, "Campaign Param",
      "api/v1/getcompaignparam"), RANKER_OUTPUT(3, "Ranker Output", "getrankeroutput"), VALUE_ANALYSIS(
      4, "Value Analysis", "getvalueanalysis"), UNSUPPORTED_TYPE(5, "UNSUPPORTED TYPE",
      "api/v1/errorhandler");

  private int id;
  private String name;
  private String resource;

  ApplicationMessageType(int id, String name, String resource) {
    this.id = id;
    this.name = name;
    this.resource = resource;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getResource() {
    return resource;
  }

}
