package com.demo.console.models.json;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TargetAudience {

  private Long audienceId;
  private Long tvCampaignInfoId;
  private String name;
  private BigDecimal adultComposition;
  private String mriSet;
  private String npmMriRelation;
  private List<String> audienceParameters;
  private String lastUpdatedDate;
  private Boolean draft;

  public Long getAudienceId() {
    return audienceId;
  }

  public void setAudienceId(Long audienceId) {
    this.audienceId = audienceId;
  }

  public Long getTvCampaignInfoId() {
    return tvCampaignInfoId;
  }

  public void setTvCampaignInfoId(Long tvCampaignInfoId) {
    this.tvCampaignInfoId = tvCampaignInfoId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMriSet() {
    return mriSet;
  }

  public void setMriSet(String mriSet) {
    this.mriSet = mriSet;
  }

  public BigDecimal getAdultComposition() {
    return adultComposition;
  }

  public void setAdultComposition(BigDecimal adultComposition) {
    this.adultComposition = adultComposition;
  }

  public List<String> getAudienceParameters() {
    if (audienceParameters == null) {
      audienceParameters = new ArrayList<String>();
    }
    return audienceParameters;
  }

  public void setAudienceParameters(List<String> audienceParameters) {
    this.audienceParameters = audienceParameters;
  }

  public String getLastUpdatedDate() {
    return lastUpdatedDate;
  }

  public void setLastUpdatedDate(String lastUpdatedDate) {
    this.lastUpdatedDate = lastUpdatedDate;
  }

  public Boolean isDraft() {
    return draft;
  }

  public void setDraft(Boolean draft) {
    this.draft = draft;
  }

  public String getNpmMriRelation() {
    return npmMriRelation;
  }

  public void setNpmMriRelation(String npmMriRelation) {
    this.npmMriRelation = npmMriRelation;
  }

}
