package com.demo.console.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "target_audience")
@NamedQuery(name = "TargetAudience.findAll", query = "SELECT a FROM TargetAudience a")
public class TargetAudience implements Serializable, BaseDaoObject {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "audience_id")
  private long audienceId;

  @Column(name = "adult_composition")
  private BigDecimal adultComposition;

  @Column(name = "mri_set")
  private String mriSet;

  @Column(name = "audience_name")
  private String audienceName;

  @Column(name = "updated_by")
  private String updatedBy;

  @Column(name = "updated_date")
  private Timestamp updatedDate;

  @Column(name = "is_draft")
  private boolean draft;

  @Column(name = "npm_mri_relation")
  private String npmMriRelation;

  public TargetAudience() {
  }

  public BigDecimal getAdultComposition() {
    return this.adultComposition;
  }

  public void setAdultComposition(BigDecimal adultComposition) {
    this.adultComposition = adultComposition;
  }

  public String getAudienceName() {
    return this.audienceName;
  }

  public void setAudienceName(String audienceName) {
    this.audienceName = audienceName;
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

  @Override
  public Long getId() {
    return this.audienceId;
  }

  @Override
  public String getPk() {
    return null;
  }

  public void setAudienceId(long audienceId) {
    this.audienceId = audienceId;
  }

  public String getMriSet() {
    return mriSet;
  }

  public void setMriSet(String mriSet) {
    this.mriSet = mriSet;
  }

  public boolean isDraft() {
    return draft;
  }

  public void setDraft(boolean draft) {
    this.draft = draft;
  }

  public String getNpmMriRelation() {
    return npmMriRelation;
  }

  public void setNpmMriRelation(String npmMriRelation) {
    this.npmMriRelation = npmMriRelation;
  }

}