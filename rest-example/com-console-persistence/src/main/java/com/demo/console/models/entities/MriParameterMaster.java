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
@Table(name = "mri_parameter_master")
@NamedQuery(name = "MriParameterMaster.findAll", query = "SELECT a FROM MriParameterMaster a")
public class MriParameterMaster implements Serializable, BaseDaoObject {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "mri_dictionary_id")
  private Long mriDictionaryId;

  @Column(name = "mri_class")
  private String mriClass;

  @Column(name = "mri_category")
  private String mriCategory;

  @Column(name = "mri_variable")
  private String mriVariable;

  @Column(name = "mri_label")
  private String mriLabel;

  @Column(name = "updated_by")
  private String updatedBy;

  @Column(name = "updated_date")
  private Timestamp updatedDate;
  
  @Column(name = "dictionary_month_start")
  private Timestamp dictionaryMonthStart;
  
  @Column(name = "dictionary_month_end")
  private Timestamp dictionaryMonthEnd;

  public MriParameterMaster() {
  }

  public MriParameterMaster(Long mriDictionaryId, String mriClass, String mriCategory,
      String mriVariable, String mriLabel, Timestamp updatedDate, String updatedBy) {
    this.mriDictionaryId = mriDictionaryId;
    this.mriClass = mriClass;
    this.mriCategory = mriCategory;
    this.mriVariable = mriVariable;
    this.mriLabel = mriLabel;
    this.updatedDate = updatedDate;
    this.updatedBy = updatedBy;
  }

  @Override
  public Long getId() {
    return mriDictionaryId;
  }

  @Override
  public String getPk() {
    return mriDictionaryId.toString();
  }

  public Long getMriDictionaryId() {
    return mriDictionaryId;
  }

  public void setMriDictionaryId(Long mriDictionaryId) {
    this.mriDictionaryId = mriDictionaryId;
  }

  public String getMriClass() {
    return mriClass;
  }

  public void setMriClass(String mriClass) {
    this.mriClass = mriClass;
  }

  public String getMriCategory() {
    return mriCategory;
  }

  public void setMriCategory(String mriCategory) {
    this.mriCategory = mriCategory;
  }

  public String getMriVariable() {
    return mriVariable;
  }

  public void setMriVariable(String mriVariable) {
    this.mriVariable = mriVariable;
  }

  public String getMriLabel() {
    return mriLabel;
  }

  public void setMriLabel(String mriLabel) {
    this.mriLabel = mriLabel;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Timestamp getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Timestamp updatedDate) {
    this.updatedDate = updatedDate;
  }
  
  public Timestamp getDictionaryMonthStart() {
    return dictionaryMonthStart;
  }

  public void setDictionaryMonthStart(Timestamp dictionaryMonthStart) {
    this.dictionaryMonthStart = dictionaryMonthStart;
  }

  public Timestamp getDictionaryMonthEnd() {
    return dictionaryMonthEnd;
  }

  public void setDictionaryMonthEnd(Timestamp dictionaryMonthEnd) {
    this.dictionaryMonthEnd = dictionaryMonthEnd;
  }
  
  
  

}