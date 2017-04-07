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
@Table(name = "parent_dbuy_network_master")
@NamedQuery(name = "DirectbuyParentNetworkMaster.findAll", query = "SELECT a FROM DirectbuyParentNetworkMaster a")
public class DirectbuyParentNetworkMaster implements Serializable, BaseDaoObject {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "p_dbuy_network_id")
  private Long dbuyNetworkId;

  @Column(name = "network")
  private String network;

  @Column(name = "parent_network")
  private String parentNetwork;

  @Column(name = "updated_by")
  private String updatedBy;

  @Column(name = "updated_date")
  private Timestamp updatedDate;

  public DirectbuyParentNetworkMaster() {
  }

  @Override
  public Long getId() {
    return dbuyNetworkId;
  }

  @Override
  public String getPk() {
    return dbuyNetworkId.toString();
  }

  public Long getDbuyNetworkId() {
    return dbuyNetworkId;
  }

  public void setDbuyNetworkId(Long dbuyNetworkId) {
    this.dbuyNetworkId = dbuyNetworkId;
  }

  public String getNetwork() {
    return network;
  }

  public void setNetwork(String network) {
    this.network = network;
  }

  public String getParentNetwork() {
    return parentNetwork;
  }

  public void setParentNetwork(String parentNetwork) {
    this.parentNetwork = parentNetwork;
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

}