package com.demo.console.models.data;

public class TokenInfo {
  private long campaignId;
  private long time;

  public long getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(long campaignId) {
    this.campaignId = campaignId;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "TokenInfo [campaignId=" + campaignId + ", time=" + time + "]";
  }

}
