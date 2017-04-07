package com.demo.console.models.data;

import java.util.List;

public class ErrorCommonBean {

  private Object value;
  private int rowNo;
  private int colNo;
  private List<String> suggestion;
  private String erroDesc;

  public ErrorCommonBean() {

  }

  public ErrorCommonBean(Object value, int rowNo, int colNo) {
    super();
    this.value = value;
    this.rowNo = rowNo;
    this.colNo = colNo;
  }

  public List<String> getSuggestion() {
    return suggestion;
  }

  public void setSuggestion(List<String> suggestion) {
    this.suggestion = suggestion;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String getErroDesc() {
    return erroDesc;
  }

  public void setErroDesc(String erroDesc) {
    this.erroDesc = erroDesc;
  }

  public int getRowNo() {
    return rowNo;
  }

  public void setRowNo(int rowNo) {
    this.rowNo = rowNo;
  }

  public int getColNo() {
    return colNo;
  }

  public void setColNo(int colNo) {
    this.colNo = colNo;
  }

}
