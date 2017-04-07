package com.demo.console.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "campaign")
@NamedQuery(name = "Campaign.findAll", query = "SELECT a FROM Campaign a")
public class Campaign implements Serializable, BaseDaoObject {

    private static final long serialVersionUID = -2343427676394069141L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "campaign_id")
    private long campaignId;

    @Column(name = "advertiser_id")
    private String advertiserId;

    private BigDecimal budget;

    @Column(name = "campaign_name")
    private String campaignName;

    @Column(name = "cpm_goal")
    private BigDecimal cpmGoal;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    @Column(name = "baseplan_file_name")
    private String baseplanFileName;

    @Column(name = "campaign_status")
    private String campaignStatus;

    @Column(name = "ranker_va_status")
    private String rankerVAStatus;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "audience_id")
    private TargetAudience targetAudience;

    // Variables declarations for Direct Buy
    @Column(name = "grand_total_net_q1")
    private Double grandTotalNetQ1;

    @Column(name = "grand_total_net_q2")
    private Double grandTotalNetQ2;

    @Column(name = "grand_total_net_q3")
    private Double grandTotalNetQ3;

    @Column(name = "grand_total_net_q4")
    private Double grandTotalNetQ4;

    @Column(name = "grand_total_net_total")
    private Double grandTotalNetTotal;

    @Column(name = "grand_total_gross_q1")
    private Double grandTotalGrossQ1;

    @Column(name = "grand_total_gross_q2")
    private Double grandTotalGrossQ2;

    @Column(name = "grand_total_gross_q3")
    private Double grandTotalGrossQ3;

    @Column(name = "grand_total_gross_q4")
    private Double grandTotalGrossQ4;

    @Column(name = "grand_total_gross_total")
    private Double grandTotalGrossTotal;

    @Column(name = "percent_by_quarter_q1")
    private Double percentByQuarterQ1;

    @Column(name = "percent_by_quarter_q2")
    private Double percentByQuarterQ2;

    @Column(name = "percent_by_quarter_q3")
    private Double percentByQuarterQ3;

    @Column(name = "percent_by_quarter_q4")
    private Double percentByQuarterQ4;

    @Column(name = "percent_by_quarter_total")
    private Double percentByQuarterTotal;

    // Variables for Net calculation
    @Column(name = "net_actual_budget_q1")
    private Double netActualBudgetQ1;

    @Column(name = "net_actual_budget_q2")
    private Double netActualBudgetQ2;

    @Column(name = "net_actual_budget_q3")
    private Double netActualBudgetQ3;

    @Column(name = "net_actual_budget_q4")
    private Double netActualBudgetQ4;

    @Column(name = "net_actual_budget_total")
    private Double netActualBudgetTotal;

    @Column(name = "net_planned_budget_q1")
    private Double netPlannedBudgetQ1;

    @Column(name = "net_planned_budget_q2")
    private Double netPlannedBudgetQ2;

    @Column(name = "net_planned_budget_q3")
    private Double netPlannedBudgetQ3;

    @Column(name = "net_planned_budget_q4")
    private Double netPlannedBudgetQ4;

    @Column(name = "net_planned_budget_total")
    private Double netPlannedBudgetTotal;

    @Column(name = "net_plan_cpm_equiv_q1")
    private Double netPlanCpmEquivQ1;

    @Column(name = "net_plan_cpm_equiv_q2")
    private Double netPlanCpmEquivQ2;

    @Column(name = "net_plan_cpm_equiv_q3")
    private Double netPlanCpmEquivQ3;

    @Column(name = "net_plan_cpm_equiv_q4")
    private Double netPlanCpmEquivQ4;

    @Column(name = "net_plan_cpm_equiv_total")
    private Double netPlanCpmEquivTotal;

    @Column(name = "net_actual_cpm_equiv_q1")
    private Double netActualCpmEquivQ1;

    @Column(name = "net_actual_cpm_equiv_q2")
    private Double netActualCpmEquivQ2;

    @Column(name = "net_actual_cpm_equiv_q3")
    private Double netActualCpmEquivQ3;

    @Column(name = "net_actual_cpm_equiv_q4")
    private Double netActualCpmEquivQ4;

    @Column(name = "net_actual_cpm_equiv_total")
    private Double netActualCpmEquivTotal;

    @Column(name = "net_plan_grps_equiv_q1")
    private Double netPlanGRPsEquivQ1;

    @Column(name = "net_plan_grps_equiv_q2")
    private Double netPlanGRPsEquivQ2;

    @Column(name = "net_plan_grps_equiv_q3")
    private Double netPlanGRPsEquivQ3;

    @Column(name = "net_plan_grps_equiv_q4")
    private Double netPlanGRPsEquivQ4;

    @Column(name = "net_plan_grps_equiv_total")
    private Double netPlanGRPsEquivTotal;

    @Column(name = "net_actual_grps_equiv_q1")
    private Double netActualGRPsEquivQ1;

    @Column(name = "net_actual_grps_equiv_q2")
    private Double netActualGRPsEquivQ2;

    @Column(name = "net_actual_grps_equiv_q3")
    private Double netActualGRPsEquivQ3;

    @Column(name = "net_actual_grps_equiv_q4")
    private Double netActualGRPsEquivQ4;

    @Column(name = "net_actual_grps_equiv_total")
    private Double netActualGRPsEquivTotal;

    @Column(name = "net_index_q1")
    private Double netIndexQ1;

    @Column(name = "net_index_q2")
    private Double netIndexQ2;

    @Column(name = "net_index_q3")
    private Double netIndexQ3;

    @Column(name = "net_index_q4")
    private Double netIndexQ4;

    @Column(name = "net_index_total")
    private Double netIndexTotal;

    @Column(name = "net_unit_count_q1")
    private Double netUnitCountQ1;

    @Column(name = "net_unit_count_q2")
    private Double netUnitCountQ2;

    @Column(name = "net_unit_count_q3")
    private Double netUnitCountQ3;

    @Column(name = "net_unit_count_q4")
    private Double netUnitCountQ4;

    @Column(name = "net_unit_count_total")
    private Double netUnitCountTotal;

    @Column(name = "net_plan_cpm_raw_q1")
    private Double netPlanCpmRawQ1;

    @Column(name = "net_plan_cpm_raw_q2")
    private Double netPlanCpmRawQ2;

    @Column(name = "net_plan_cpm_raw_q3")
    private Double netPlanCpmRawQ3;

    @Column(name = "net_plan_cpm_raw_q4")
    private Double netPlanCpmRawQ4;

    @Column(name = "net_plan_cpm_raw_total")
    private Double netPlanCpmRawTotal;

    @Column(name = "net_actual_cpm_raw_q1")
    private Double netActualCpmRawQ1;

    @Column(name = "net_actual_cpm_raw_q2")
    private Double netActualCpmRawQ2;

    @Column(name = "net_actual_cpm_raw_q3")
    private Double netActualCpmRawQ3;

    @Column(name = "net_actual_cpm_raw_q4")
    private Double netActualCpmRawQ4;

    @Column(name = "net_actual_cpm_raw_total")
    private Double netActualCpmRawTotal;

    @Column(name = "net_plan_grps_raw_q1")
    private Double netPlanGRPsRawQ1;

    @Column(name = "net_plan_grps_raw_q2")
    private Double netPlanGRPsRawQ2;

    @Column(name = "net_plan_grps_raw_q3")
    private Double netPlanGRPsRawQ3;

    @Column(name = "net_plan_grps_raw_q4")
    private Double netPlanGRPsRawQ4;

    @Column(name = "net_plan_grps_raw_total")
    private Double netPlanGRPsRawTotal;

    @Column(name = "net_actual_grps_raw_q1")
    private Double netActualGRPsRawQ1;

    @Column(name = "net_actual_grps_raw_q2")
    private Double netActualGRPsRawQ2;

    @Column(name = "net_actual_grps_raw_q3")
    private Double netActualGRPsRawQ3;

    @Column(name = "net_actual_grps_raw_q4")
    private Double netActualGRPsRawQ4;

    @Column(name = "net_actual_grps_raw_total")
    private Double netActualGRPsRawTotal;

    @Column(name = "net_index_raw_q1")
    private Double netIndexRawQ1;

    @Column(name = "net_index_raw_q2")
    private Double netIndexRawQ2;

    @Column(name = "net_index_raw_q3")
    private Double netIndexRawQ3;

    @Column(name = "net_index_raw_q4")
    private Double netIndexRawQ4;

    @Column(name = "net_index_raw_total")
    private Double netIndexRawTotal;

    @Column(name = "campaign_type")
    private String campaignType;

    @Column(name = "buy_demo")
    private String buyDemo;

    @Column(name = "is_directbuy_active")
    private boolean isDirectbuyActive;

    @Transient
    List<RunDate> runDates;

    public Campaign() {
    }

    public Campaign(Campaign campaign) {

	setAdvertiserId(campaign.getAdvertiserId());
	setTargetAudience(campaign.getTargetAudience());
	setBudget(campaign.getBudget());
	setCampaignName(campaign.getCampaignName());
	setCpmGoal(campaign.getCpmGoal());
	setUpdatedBy(campaign.getUpdatedBy());
	setUpdatedDate(campaign.getUpdatedDate());
	setRunDates(campaign.getRunDates());
    }

    public String getAdvertiserId() {
	return this.advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
	this.advertiserId = advertiserId;
    }

    public BigDecimal getBudget() {
	return this.budget;
    }

    public void setBudget(BigDecimal budget) {
	this.budget = budget;
    }

    public String getCampaignName() {
	return this.campaignName;
    }

    public void setCampaignName(String campaignName) {
	this.campaignName = campaignName;
    }

    public BigDecimal getCpmGoal() {
	return this.cpmGoal;
    }

    public void setCpmGoal(BigDecimal cpmGoal) {
	this.cpmGoal = cpmGoal;
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

    public TargetAudience getTargetAudience() {
	return this.targetAudience;
    }

    public void setTargetAudience(TargetAudience targetAudience) {
	this.targetAudience = targetAudience;
    }

    @Override
    public Long getId() {
	return this.campaignId;
    }

    @Override
    public String getPk() {
	return null;
    }

    public List<RunDate> getRunDates() {
	return runDates;
    }

    public void setRunDates(List<RunDate> runDates) {
	this.runDates = runDates;
    }

    public long getCampaignId() {
	return campaignId;
    }

    public void setCampaignId(long campaignId) {
	this.campaignId = campaignId;
    }

    public String getCampaignStatus() {
	return campaignStatus;
    }

    public void setCampaignStatus(String campaignStatus) {
	this.campaignStatus = campaignStatus;
    }

    public Timestamp getCreatedDate() {
	return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
	this.createdDate = createdDate;
    }

    public String getCreatedBy() {
	return createdBy;
    }

    public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
    }

    public String getBaseplanFileName() {
	return baseplanFileName;
    }

    public void setBaseplanFileName(String baseplanFileName) {
	this.baseplanFileName = baseplanFileName;
    }

    public Double getGrandTotalNetQ1() {
	return grandTotalNetQ1;
    }

    public void setGrandTotalNetQ1(Double grandTotalNetQ1) {
	this.grandTotalNetQ1 = grandTotalNetQ1;
    }

    public Double getGrandTotalNetQ2() {
	return grandTotalNetQ2;
    }

    public void setGrandTotalNetQ2(Double grandTotalNetQ2) {
	this.grandTotalNetQ2 = grandTotalNetQ2;
    }

    public Double getGrandTotalNetQ3() {
	return grandTotalNetQ3;
    }

    public void setGrandTotalNetQ3(Double grandTotalNetQ3) {
	this.grandTotalNetQ3 = grandTotalNetQ3;
    }

    public Double getGrandTotalNetQ4() {
	return grandTotalNetQ4;
    }

    public void setGrandTotalNetQ4(Double grandTotalNetQ4) {
	this.grandTotalNetQ4 = grandTotalNetQ4;
    }

    public Double getGrandTotalNetTotal() {
	return grandTotalNetTotal;
    }

    public void setGrandTotalNetTotal(Double grandTotalNetTotal) {
	this.grandTotalNetTotal = grandTotalNetTotal;
    }

    public Double getGrandTotalGrossQ1() {
	return grandTotalGrossQ1;
    }

    public void setGrandTotalGrossQ1(Double grandTotalGrossQ1) {
	this.grandTotalGrossQ1 = grandTotalGrossQ1;
    }

    public Double getGrandTotalGrossQ2() {
	return grandTotalGrossQ2;
    }

    public void setGrandTotalGrossQ2(Double grandTotalGrossQ2) {
	this.grandTotalGrossQ2 = grandTotalGrossQ2;
    }

    public Double getGrandTotalGrossQ3() {
	return grandTotalGrossQ3;
    }

    public void setGrandTotalGrossQ3(Double grandTotalGrossQ3) {
	this.grandTotalGrossQ3 = grandTotalGrossQ3;
    }

    public Double getGrandTotalGrossQ4() {
	return grandTotalGrossQ4;
    }

    public void setGrandTotalGrossQ4(Double grandTotalGrossQ4) {
	this.grandTotalGrossQ4 = grandTotalGrossQ4;
    }

    public Double getGrandTotalGrossTotal() {
	return grandTotalGrossTotal;
    }

    public void setGrandTotalGrossTotal(Double grandTotalGrossTotal) {
	this.grandTotalGrossTotal = grandTotalGrossTotal;
    }

    public Double getPercentByQuarterQ1() {
	return percentByQuarterQ1;
    }

    public void setPercentByQuarterQ1(Double percentByQuarterQ1) {
	this.percentByQuarterQ1 = percentByQuarterQ1;
    }

    public Double getPercentByQuarterQ2() {
	return percentByQuarterQ2;
    }

    public void setPercentByQuarterQ2(Double percentByQuarterQ2) {
	this.percentByQuarterQ2 = percentByQuarterQ2;
    }

    public Double getPercentByQuarterQ3() {
	return percentByQuarterQ3;
    }

    public void setPercentByQuarterQ3(Double percentByQuarterQ3) {
	this.percentByQuarterQ3 = percentByQuarterQ3;
    }

    public Double getPercentByQuarterQ4() {
	return percentByQuarterQ4;
    }

    public void setPercentByQuarterQ4(Double percentByQuarterQ4) {
	this.percentByQuarterQ4 = percentByQuarterQ4;
    }

    public Double getPercentByQuarterTotal() {
	return percentByQuarterTotal;
    }

    public void setPercentByQuarterTotal(Double percentByQuarterTotal) {
	this.percentByQuarterTotal = percentByQuarterTotal;
    }

    public Double getNetActualBudgetQ1() {
	return netActualBudgetQ1;
    }

    public void setNetActualBudgetQ1(Double netActualBudgetQ1) {
	this.netActualBudgetQ1 = netActualBudgetQ1;
    }

    public Double getNetActualBudgetQ2() {
	return netActualBudgetQ2;
    }

    public void setNetActualBudgetQ2(Double netActualBudgetQ2) {
	this.netActualBudgetQ2 = netActualBudgetQ2;
    }

    public Double getNetActualBudgetQ3() {
	return netActualBudgetQ3;
    }

    public void setNetActualBudgetQ3(Double netActualBudgetQ3) {
	this.netActualBudgetQ3 = netActualBudgetQ3;
    }

    public Double getNetActualBudgetQ4() {
	return netActualBudgetQ4;
    }

    public void setNetActualBudgetQ4(Double netActualBudgetQ4) {
	this.netActualBudgetQ4 = netActualBudgetQ4;
    }

    public Double getNetActualBudgetTotal() {
	return netActualBudgetTotal;
    }

    public void setNetActualBudgetTotal(Double netActualBudgetTotal) {
	this.netActualBudgetTotal = netActualBudgetTotal;
    }

    public Double getNetPlannedBudgetQ1() {
	return netPlannedBudgetQ1;
    }

    public void setNetPlannedBudgetQ1(Double netPlannedBudgetQ1) {
	this.netPlannedBudgetQ1 = netPlannedBudgetQ1;
    }

    public Double getNetPlannedBudgetQ2() {
	return netPlannedBudgetQ2;
    }

    public void setNetPlannedBudgetQ2(Double netPlannedBudgetQ2) {
	this.netPlannedBudgetQ2 = netPlannedBudgetQ2;
    }

    public Double getNetPlannedBudgetQ3() {
	return netPlannedBudgetQ3;
    }

    public void setNetPlannedBudgetQ3(Double netPlannedBudgetQ3) {
	this.netPlannedBudgetQ3 = netPlannedBudgetQ3;
    }

    public Double getNetPlannedBudgetQ4() {
	return netPlannedBudgetQ4;
    }

    public void setNetPlannedBudgetQ4(Double netPlannedBudgetQ4) {
	this.netPlannedBudgetQ4 = netPlannedBudgetQ4;
    }

    public Double getNetPlannedBudgetTotal() {
	return netPlannedBudgetTotal;
    }

    public void setNetPlannedBudgetTotal(Double netPlannedBudgetTotal) {
	this.netPlannedBudgetTotal = netPlannedBudgetTotal;
    }

    public Double getNetPlanCpmEquivQ1() {
	return netPlanCpmEquivQ1;
    }

    public void setNetPlanCpmEquivQ1(Double netPlanCpmEquivQ1) {
	this.netPlanCpmEquivQ1 = netPlanCpmEquivQ1;
    }

    public Double getNetPlanCpmEquivQ2() {
	return netPlanCpmEquivQ2;
    }

    public void setNetPlanCpmEquivQ2(Double netPlanCpmEquivQ2) {
	this.netPlanCpmEquivQ2 = netPlanCpmEquivQ2;
    }

    public Double getNetPlanCpmEquivQ3() {
	return netPlanCpmEquivQ3;
    }

    public void setNetPlanCpmEquivQ3(Double netPlanCpmEquivQ3) {
	this.netPlanCpmEquivQ3 = netPlanCpmEquivQ3;
    }

    public Double getNetPlanCpmEquivQ4() {
	return netPlanCpmEquivQ4;
    }

    public void setNetPlanCpmEquivQ4(Double netPlanCpmEquivQ4) {
	this.netPlanCpmEquivQ4 = netPlanCpmEquivQ4;
    }

    public Double getNetPlanCpmEquivTotal() {
	return netPlanCpmEquivTotal;
    }

    public void setNetPlanCpmEquivTotal(Double netPlanCpmEquivTotal) {
	this.netPlanCpmEquivTotal = netPlanCpmEquivTotal;
    }

    public Double getNetActualCpmEquivQ1() {
	return netActualCpmEquivQ1;
    }

    public void setNetActualCpmEquivQ1(Double netActualCpmEquivQ1) {
	this.netActualCpmEquivQ1 = netActualCpmEquivQ1;
    }

    public Double getNetActualCpmEquivQ2() {
	return netActualCpmEquivQ2;
    }

    public void setNetActualCpmEquivQ2(Double netActualCpmEquivQ2) {
	this.netActualCpmEquivQ2 = netActualCpmEquivQ2;
    }

    public Double getNetActualCpmEquivQ3() {
	return netActualCpmEquivQ3;
    }

    public void setNetActualCpmEquivQ3(Double netActualCpmEquivQ3) {
	this.netActualCpmEquivQ3 = netActualCpmEquivQ3;
    }

    public Double getNetActualCpmEquivQ4() {
	return netActualCpmEquivQ4;
    }

    public void setNetActualCpmEquivQ4(Double netActualCpmEquivQ4) {
	this.netActualCpmEquivQ4 = netActualCpmEquivQ4;
    }

    public Double getNetActualCpmEquivTotal() {
	return netActualCpmEquivTotal;
    }

    public void setNetActualCpmEquivTotal(Double netActualCpmEquivTotal) {
	this.netActualCpmEquivTotal = netActualCpmEquivTotal;
    }

    public Double getNetPlanGRPsEquivQ1() {
	return netPlanGRPsEquivQ1;
    }

    public void setNetPlanGRPsEquivQ1(Double netPlanGRPsEquivQ1) {
	this.netPlanGRPsEquivQ1 = netPlanGRPsEquivQ1;
    }

    public Double getNetPlanGRPsEquivQ2() {
	return netPlanGRPsEquivQ2;
    }

    public void setNetPlanGRPsEquivQ2(Double netPlanGRPsEquivQ2) {
	this.netPlanGRPsEquivQ2 = netPlanGRPsEquivQ2;
    }

    public Double getNetPlanGRPsEquivQ3() {
	return netPlanGRPsEquivQ3;
    }

    public void setNetPlanGRPsEquivQ3(Double netPlanGRPsEquivQ3) {
	this.netPlanGRPsEquivQ3 = netPlanGRPsEquivQ3;
    }

    public Double getNetPlanGRPsEquivQ4() {
	return netPlanGRPsEquivQ4;
    }

    public void setNetPlanGRPsEquivQ4(Double netPlanGRPsEquivQ4) {
	this.netPlanGRPsEquivQ4 = netPlanGRPsEquivQ4;
    }

    public Double getNetPlanGRPsEquivTotal() {
	return netPlanGRPsEquivTotal;
    }

    public void setNetPlanGRPsEquivTotal(Double netPlanGRPsEquivTotal) {
	this.netPlanGRPsEquivTotal = netPlanGRPsEquivTotal;
    }

    public Double getNetActualGRPsEquivQ1() {
	return netActualGRPsEquivQ1;
    }

    public void setNetActualGRPsEquivQ1(Double netActualGRPsEquivQ1) {
	this.netActualGRPsEquivQ1 = netActualGRPsEquivQ1;
    }

    public Double getNetActualGRPsEquivQ2() {
	return netActualGRPsEquivQ2;
    }

    public void setNetActualGRPsEquivQ2(Double netActualGRPsEquivQ2) {
	this.netActualGRPsEquivQ2 = netActualGRPsEquivQ2;
    }

    public Double getNetActualGRPsEquivQ3() {
	return netActualGRPsEquivQ3;
    }

    public void setNetActualGRPsEquivQ3(Double netActualGRPsEquivQ3) {
	this.netActualGRPsEquivQ3 = netActualGRPsEquivQ3;
    }

    public Double getNetActualGRPsEquivQ4() {
	return netActualGRPsEquivQ4;
    }

    public void setNetActualGRPsEquivQ4(Double netActualGRPsEquivQ4) {
	this.netActualGRPsEquivQ4 = netActualGRPsEquivQ4;
    }

    public Double getNetActualGRPsEquivTotal() {
	return netActualGRPsEquivTotal;
    }

    public void setNetActualGRPsEquivTotal(Double netActualGRPsEquivTotal) {
	this.netActualGRPsEquivTotal = netActualGRPsEquivTotal;
    }

    public Double getNetIndexQ1() {
	return netIndexQ1;
    }

    public void setNetIndexQ1(Double netIndexQ1) {
	this.netIndexQ1 = netIndexQ1;
    }

    public Double getNetIndexQ2() {
	return netIndexQ2;
    }

    public void setNetIndexQ2(Double netIndexQ2) {
	this.netIndexQ2 = netIndexQ2;
    }

    public Double getNetIndexQ3() {
	return netIndexQ3;
    }

    public void setNetIndexQ3(Double netIndexQ3) {
	this.netIndexQ3 = netIndexQ3;
    }

    public Double getNetIndexQ4() {
	return netIndexQ4;
    }

    public void setNetIndexQ4(Double netIndexQ4) {
	this.netIndexQ4 = netIndexQ4;
    }

    public Double getNetIndexTotal() {
	return netIndexTotal;
    }

    public void setNetIndexTotal(Double netIndexTotal) {
	this.netIndexTotal = netIndexTotal;
    }

    public Double getNetUnitCountQ1() {
	return netUnitCountQ1;
    }

    public void setNetUnitCountQ1(Double netUnitCountQ1) {
	this.netUnitCountQ1 = netUnitCountQ1;
    }

    public Double getNetUnitCountQ2() {
	return netUnitCountQ2;
    }

    public void setNetUnitCountQ2(Double netUnitCountQ2) {
	this.netUnitCountQ2 = netUnitCountQ2;
    }

    public Double getNetUnitCountQ3() {
	return netUnitCountQ3;
    }

    public void setNetUnitCountQ3(Double netUnitCountQ3) {
	this.netUnitCountQ3 = netUnitCountQ3;
    }

    public Double getNetUnitCountQ4() {
	return netUnitCountQ4;
    }

    public void setNetUnitCountQ4(Double netUnitCountQ4) {
	this.netUnitCountQ4 = netUnitCountQ4;
    }

    public Double getNetUnitCountTotal() {
	return netUnitCountTotal;
    }

    public void setNetUnitCountTotal(Double netUnitCountTotal) {
	this.netUnitCountTotal = netUnitCountTotal;
    }

    public Double getNetPlanCpmRawQ1() {
	return netPlanCpmRawQ1;
    }

    public void setNetPlanCpmRawQ1(Double netPlanCpmRawQ1) {
	this.netPlanCpmRawQ1 = netPlanCpmRawQ1;
    }

    public Double getNetPlanCpmRawQ2() {
	return netPlanCpmRawQ2;
    }

    public void setNetPlanCpmRawQ2(Double netPlanCpmRawQ2) {
	this.netPlanCpmRawQ2 = netPlanCpmRawQ2;
    }

    public Double getNetPlanCpmRawQ3() {
	return netPlanCpmRawQ3;
    }

    public void setNetPlanCpmRawQ3(Double netPlanCpmRawQ3) {
	this.netPlanCpmRawQ3 = netPlanCpmRawQ3;
    }

    public Double getNetPlanCpmRawQ4() {
	return netPlanCpmRawQ4;
    }

    public void setNetPlanCpmRawQ4(Double netPlanCpmRawQ4) {
	this.netPlanCpmRawQ4 = netPlanCpmRawQ4;
    }

    public Double getNetPlanCpmRawTotal() {
	return netPlanCpmRawTotal;
    }

    public void setNetPlanCpmRawTotal(Double netPlanCpmRawTotal) {
	this.netPlanCpmRawTotal = netPlanCpmRawTotal;
    }

    public Double getNetActualCpmRawQ1() {
	return netActualCpmRawQ1;
    }

    public void setNetActualCpmRawQ1(Double netActualCpmRawQ1) {
	this.netActualCpmRawQ1 = netActualCpmRawQ1;
    }

    public Double getNetActualCpmRawQ2() {
	return netActualCpmRawQ2;
    }

    public void setNetActualCpmRawQ2(Double netActualCpmRawQ2) {
	this.netActualCpmRawQ2 = netActualCpmRawQ2;
    }

    public Double getNetActualCpmRawQ3() {
	return netActualCpmRawQ3;
    }

    public void setNetActualCpmRawQ3(Double netActualCpmRawQ3) {
	this.netActualCpmRawQ3 = netActualCpmRawQ3;
    }

    public Double getNetActualCpmRawQ4() {
	return netActualCpmRawQ4;
    }

    public void setNetActualCpmRawQ4(Double netActualCpmRawQ4) {
	this.netActualCpmRawQ4 = netActualCpmRawQ4;
    }

    public Double getNetActualCpmRawTotal() {
	return netActualCpmRawTotal;
    }

    public void setNetActualCpmRawTotal(Double netActualCpmRawTotal) {
	this.netActualCpmRawTotal = netActualCpmRawTotal;
    }

    public Double getNetPlanGRPsRawQ1() {
	return netPlanGRPsRawQ1;
    }

    public void setNetPlanGRPsRawQ1(Double netPlanGRPsRawQ1) {
	this.netPlanGRPsRawQ1 = netPlanGRPsRawQ1;
    }

    public Double getNetPlanGRPsRawQ2() {
	return netPlanGRPsRawQ2;
    }

    public void setNetPlanGRPsRawQ2(Double netPlanGRPsRawQ2) {
	this.netPlanGRPsRawQ2 = netPlanGRPsRawQ2;
    }

    public Double getNetPlanGRPsRawQ3() {
	return netPlanGRPsRawQ3;
    }

    public void setNetPlanGRPsRawQ3(Double netPlanGRPsRawQ3) {
	this.netPlanGRPsRawQ3 = netPlanGRPsRawQ3;
    }

    public Double getNetPlanGRPsRawQ4() {
	return netPlanGRPsRawQ4;
    }

    public void setNetPlanGRPsRawQ4(Double netPlanGRPsRawQ4) {
	this.netPlanGRPsRawQ4 = netPlanGRPsRawQ4;
    }

    public Double getNetPlanGRPsRawTotal() {
	return netPlanGRPsRawTotal;
    }

    public void setNetPlanGRPsRawTotal(Double netPlanGRPsRawTotal) {
	this.netPlanGRPsRawTotal = netPlanGRPsRawTotal;
    }

    public Double getNetActualGRPsRawQ1() {
	return netActualGRPsRawQ1;
    }

    public void setNetActualGRPsRawQ1(Double netActualGRPsRawQ1) {
	this.netActualGRPsRawQ1 = netActualGRPsRawQ1;
    }

    public Double getNetActualGRPsRawQ2() {
	return netActualGRPsRawQ2;
    }

    public void setNetActualGRPsRawQ2(Double netActualGRPsRawQ2) {
	this.netActualGRPsRawQ2 = netActualGRPsRawQ2;
    }

    public Double getNetActualGRPsRawQ3() {
	return netActualGRPsRawQ3;
    }

    public void setNetActualGRPsRawQ3(Double netActualGRPsRawQ3) {
	this.netActualGRPsRawQ3 = netActualGRPsRawQ3;
    }

    public Double getNetActualGRPsRawQ4() {
	return netActualGRPsRawQ4;
    }

    public void setNetActualGRPsRawQ4(Double netActualGRPsRawQ4) {
	this.netActualGRPsRawQ4 = netActualGRPsRawQ4;
    }

    public Double getNetActualGRPsRawTotal() {
	return netActualGRPsRawTotal;
    }

    public void setNetActualGRPsRawTotal(Double netActualGRPsRawTotal) {
	this.netActualGRPsRawTotal = netActualGRPsRawTotal;
    }

    public Double getNetIndexRawQ1() {
	return netIndexRawQ1;
    }

    public void setNetIndexRawQ1(Double netIndexRawQ1) {
	this.netIndexRawQ1 = netIndexRawQ1;
    }

    public Double getNetIndexRawQ2() {
	return netIndexRawQ2;
    }

    public void setNetIndexRawQ2(Double netIndexRawQ2) {
	this.netIndexRawQ2 = netIndexRawQ2;
    }

    public Double getNetIndexRawQ3() {
	return netIndexRawQ3;
    }

    public void setNetIndexRawQ3(Double netIndexRawQ3) {
	this.netIndexRawQ3 = netIndexRawQ3;
    }

    public Double getNetIndexRawQ4() {
	return netIndexRawQ4;
    }

    public void setNetIndexRawQ4(Double netIndexRawQ4) {
	this.netIndexRawQ4 = netIndexRawQ4;
    }

    public Double getNetIndexRawTotal() {
	return netIndexRawTotal;
    }

    public void setNetIndexRawTotal(Double netIndexRawTotal) {
	this.netIndexRawTotal = netIndexRawTotal;
    }

    public String getCampaignType() {
	return campaignType;
    }

    public void setCampaignType(String campaignType) {
	this.campaignType = campaignType;
    }

    public String getBuyDemo() {
	return buyDemo;
    }

    public void setBuyDemo(String buyDemo) {
	this.buyDemo = buyDemo;
    }

    public boolean isDirectbuyActive() {
	return isDirectbuyActive;
    }

    public void setDirectbuyActive(boolean isDirectbuyActive) {
	this.isDirectbuyActive = isDirectbuyActive;
    }

    @Override
    public String toString() {
	return "Campaign [campaignId=" + campaignId + ", advertiserId=" + advertiserId + ", budget=" + budget + ", campaignName=" + campaignName + ", cpmGoal=" + cpmGoal + ", updatedBy=" + updatedBy
		+ ", updatedDate=" + updatedDate + ", baseplanFileName=" + baseplanFileName + ", campaignStatus=" + campaignStatus + ", rankerVAStatus=" + rankerVAStatus + ", createdDate="
		+ createdDate + ", createdBy=" + createdBy + ", ampTargetAudience=" + targetAudience + ", ampRunDates=" + runDates + "]";
    }

    public String getRankerVAStatus() {
	return rankerVAStatus;
    }

    public void setRankerVAStatus(String rankerVAStatus) {
	this.rankerVAStatus = rankerVAStatus;
    }

}