package com.demo.console.models.json;

import java.math.BigDecimal;

public class RunDateJson {

  private String endDate;

  private String startDate;

  private BigDecimal budget;
  private Long runDateId;

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public BigDecimal getBudget() {
    return budget;
  }

  public void setBudget(BigDecimal budget) {
    this.budget = budget;
  }

  public Long getRunDateId() {
    return runDateId;
  }

  public void setRunDateId(Long runDateId) {
    this.runDateId = runDateId;
  }

}
