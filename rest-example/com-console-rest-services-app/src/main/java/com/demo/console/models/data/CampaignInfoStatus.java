package com.demo.console.models.data;

public enum CampaignInfoStatus {
  draft, inprogress, ready, sending_to_ssp, sent_to_ssp;

  public static boolean contains(String s) {
    for (CampaignInfoStatus choice : values())
      if (choice.name().equalsIgnoreCase(s))
        return true;
    return false;
  }
}
