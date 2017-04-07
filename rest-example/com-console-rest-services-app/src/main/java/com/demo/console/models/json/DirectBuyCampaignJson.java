package com.demo.console.models.json;

import java.math.BigDecimal;

public class DirectBuyCampaignJson {

  private Long campaignId;

  private String advertiser;

  private BigDecimal plannedBudget;

  private String campaignName;

  private BigDecimal plannedCpm;

  private String updatedDate;

  private String campaignStatus;

  private String createdDate;

  private String createdBy;

  private BigDecimal plannedImps;

  public Long getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(Long campaignId) {
    this.campaignId = campaignId;
  }

  public String getAdvertiser() {
    return advertiser;
  }

  public void setAdvertiser(String advertiser) {
    this.advertiser = advertiser;
  }

  public BigDecimal getPlannedBudget() {
    return plannedBudget;
  }

  public void setPlannedBudget(BigDecimal plannedBudget) {
    this.plannedBudget = plannedBudget;
  }

  public String getCampaignName() {
    return campaignName;
  }

  public void setCampaignName(String campaignName) {
    this.campaignName = campaignName;
  }

  public BigDecimal getPlannedCpm() {
    return plannedCpm;
  }

  public void setPlannedCpm(BigDecimal plannedCpm) {
    this.plannedCpm = plannedCpm;
  }

  public String getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(String updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getCampaignStatus() {
    return campaignStatus;
  }

  public void setCampaignStatus(String campaignStatus) {
    this.campaignStatus = campaignStatus;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public BigDecimal getPlannedImps() {
    return plannedImps;
  }

  public void setPlannedImps(BigDecimal plannedImps) {
    this.plannedImps = plannedImps;
  }

}
