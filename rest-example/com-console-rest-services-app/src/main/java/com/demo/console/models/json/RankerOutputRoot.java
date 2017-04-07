package com.demo.console.models.json;

import java.util.List;

public class RankerOutputRoot {
  private List<String> ranker_results;

  private String value_analysis;

  private long campaignid;

public List<String> getRanker_results() {
    return ranker_results;
}

public void setRanker_results(List<String> ranker_results) {
    this.ranker_results = ranker_results;
}

public String getValue_analysis() {
    return value_analysis;
}

public void setValue_analysis(String value_analysis) {
    this.value_analysis = value_analysis;
}

public long getCampaignid() {
    return campaignid;
}

public void setCampaignid(long campaignid) {
    this.campaignid = campaignid;
}


}