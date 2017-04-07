package com.demo.console.models.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "audience_parameter_master")
@NamedQuery(name = "AudienceParameterMaster.findAll", query = "SELECT a FROM AudienceParameterMaster a")
public class AudienceParameterMaster implements Serializable, BaseDaoObject {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "audience_param_id")
  private Long audienceParamId;

  @Column(name = "audience_paramete_name")
  private String audienceParameteName;

  @Column(name = "master_parameter_name")
  private String masterParameterName;

  @Column(name = "updated_by")
  private String updatedBy;

  @Column(name = "updated_date")
  private Timestamp updatedDate;

  @Column(name = "is_default")
  private boolean isDefault;

  public AudienceParameterMaster() {
  }

  public String getAudienceParameteName() {
    return this.audienceParameteName;
  }

  public void setAudienceParameteName(String audienceParameteName) {
    this.audienceParameteName = audienceParameteName;
  }

  public String getMasterParameterName() {
    return this.masterParameterName;
  }

  public void setMasterParameterName(String masterParameterName) {
    this.masterParameterName = masterParameterName;
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
    return audienceParamId;
  }

  @Override
  public String getPk() {
    return audienceParamId.toString();
  }

  public void setAudienceParamId(Long audienceParamId) {
    this.audienceParamId = audienceParamId;
  }

  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean isDefault) {
    this.isDefault = isDefault;
  }

}