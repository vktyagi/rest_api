package com.demo.console.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "run_date")
@NamedQuery(name = "RunDate.findAll", query = "SELECT a FROM RunDate a")
public class RunDate implements Serializable, BaseDaoObject, Comparable<RunDate> {

  private static final long serialVersionUID = -6726780074482492088L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "run_date_id")
  private long runDateId;

  @Column(name = "budget")
  private BigDecimal budget;

  @Temporal(TemporalType.DATE)
  @Column(name = "end_date")
  private Date endDate;

  @Temporal(TemporalType.DATE)
  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "updated_by")
  private String updatedBy;

  @Column(name = "updated_date")
  private Timestamp updatedDate;

  @ManyToOne(cascade = { CascadeType.ALL })
  @JoinColumn(name = "campaign_id")
  private Campaign campaign;

  public RunDate() {
  }

  public BigDecimal getBudget() {
    return this.budget;
  }

  public void setBudget(BigDecimal budget) {
    this.budget = budget;
  }

  public Date getEndDate() {
    return this.endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getStartDate() {
    return this.startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getUpdatedBy() {
    return this.updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Timestamp getUpdatedDate() {
    return this.updatedDate;
  }

  public void setUpdatedDate(Timestamp updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Campaign getCampaign() {
    return this.campaign;
  }

  public void setCampaign(Campaign campaign) {
    this.campaign = campaign;
  }

  @Override
  public Long getId() {
    return this.runDateId;
  }

  @Override
  public String getPk() {
    return null;
  }

  public long getRunDateId() {
    return runDateId;
  }

  public void setRunDateId(long runDateId) {
    this.runDateId = runDateId;
  }

  @Override
  public String toString() {
    return "RunDate [runDateId=" + runDateId + ", budget=" + budget + ", endDate=" + endDate
        + ", startDate=" + startDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
        + ", Campaign=" + campaign + "]";
  }

  @Override
  public int compareTo(RunDate thatRunDate) {
    if (null == thatRunDate || null == this.startDate || null == thatRunDate.getStartDate()
        || null == this.endDate || null == thatRunDate.getEndDate()) {
      return -1;
    }

    return startDate.compareTo(thatRunDate.getStartDate());
  }

}