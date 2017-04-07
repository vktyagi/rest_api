package com.demo.console.services.impl;

import static com.demo.console.utils.ValidationUtils.DATE_FORMAT_ISO;
import static com.demo.console.utils.ValidationUtils.DATE_FORMAT_YYYYMMDD;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.console.dao.CampaignDao;
import com.demo.console.dao.RunDateDao;
import com.demo.console.models.data.CampaignInfoStatus;
import com.demo.console.models.data.CampaignType;
import com.demo.console.models.entities.Campaign;
import com.demo.console.models.entities.RunDate;
import com.demo.console.models.json.RunDateJson;
import com.demo.console.models.json.CampaignJson;
import com.demo.console.services.CampaignService;
import com.demo.console.utils.ValidationUtils;
import com.demo.console.wrapper.exception.BadRequestParameterException;
import com.demo.console.wrapper.exception.NoDataFoundException;

@Service("campaignService")
public class CampaignServiceImpl implements CampaignService {

  private static final Logger LOGGER = Logger.getLogger(CampaignServiceImpl.class);

  @Autowired
  private CampaignDao campaignDao;

  @Autowired
  private RunDateDao runDateDao;

/*  @Autowired
  private TargetAudienceDao targetAudienceDao;

  @Resource
  private InventoryDao inventorydao;

  @Resource
  private MustHaveNetworkDao mustHaveNetworkDao;

  @Resource
  private ExcludedNetworkDao excludedNetworkDao;

  @Resource
  private DaypartAllocationDao daypartAllocationDao;

  @Resource
  private AmpRankerOutputDao ampRankerOutputDao;

  @Resource
  private AmpValueAnalysisDao ampValueAnalysisDao;
  
  @Autowired
  AmpRankerValueAnalysisDao ampRankerVADao;

  @Resource
  private BasePlanDao baseplanDao;*/

  @Override
  public CampaignJson getCampaignById(Long campaignId) {

    Campaign campaign = campaignDao.getCampaignById(campaignId);

    if (null == campaign) {
      throw new NoDataFoundException("No Data found for CampaignId :" + campaignId);
    } else {
      campaign.setRunDates(runDateDao.findByCampaign(campaign));
    }

    return converToJson(campaign);

  }

 
  @Override
  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRES_NEW)
  public CampaignJson createTvCampaign(CampaignJson tvCampaignJson, String userName) {
    Campaign campaign = null;
    if (validate(tvCampaignJson)) {
      campaign = convertFromJson(campaign, tvCampaignJson);

      if (validate(campaign)) {
        campaign.setCreatedBy(userName);
        campaign.setCreatedDate(new Timestamp(new Date().getTime()));
        campaign.setUpdatedDate(new Timestamp(new Date().getTime()));
        campaign.setUpdatedBy(userName);
        campaign.setCampaignStatus(CampaignInfoStatus.draft.name());
        campaign.setCampaignType(CampaignType.ssp.name());

        campaignDao.create(campaign);

        if (null != campaign.getRunDates() && !campaign.getRunDates().isEmpty()) {
          for (RunDate runDate : campaign.getRunDates()) {
            runDate.setCampaign(campaign);
            runDate.setUpdatedBy(userName);
            runDate.setUpdatedDate(new Timestamp(new Date().getTime()));
            runDateDao.save(runDate);
          }
        }
      }
    }

    return converToJson(campaign);
  }

  private boolean validate(Campaign campaign) {

    BigDecimal runDateBudgetTotal = new BigDecimal(0);
    Date previousEndDate = null;

    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    Date currentDate = null;
    try {
      currentDate = dateFormat.parse(dateFormat.format(new Date()));
    } catch (ParseException e) {
      LOGGER.error(e);
    }

    List<RunDate> sortedRunDateList = new ArrayList<RunDate>(campaign.getRunDates());
    Collections.sort(sortedRunDateList);

    for (RunDate ard : sortedRunDateList) {

      if (ard.getStartDate().compareTo(currentDate) < 0) {
        throw new BadRequestParameterException("Start Date "
            + ValidationUtils.convertDateToString(DATE_FORMAT_YYYYMMDD, ard.getStartDate())
            + " should not be less than Current Date.");
      }

      if (ard.getEndDate().compareTo(currentDate) < 0) {
        throw new BadRequestParameterException("End Date "
            + ValidationUtils.convertDateToString(DATE_FORMAT_YYYYMMDD, ard.getEndDate())
            + " should not be less than Current Date.");
      }

      if (ard.getEndDate().compareTo(ard.getStartDate()) < 0) {
        throw new BadRequestParameterException("Start Date "
            + ValidationUtils.convertDateToString(DATE_FORMAT_YYYYMMDD, ard.getStartDate())
            + " should not be greater than End Date "
            + ValidationUtils.convertDateToString("yyyy-MM-dd", ard.getEndDate()));
      }

      if (null != previousEndDate && previousEndDate.compareTo(ard.getStartDate()) >= 0) {
        throw new BadRequestParameterException("Flight Dates "
            + ValidationUtils.convertDateToString(DATE_FORMAT_YYYYMMDD, ard.getStartDate())
            + " cannot overlap.");
      }

      previousEndDate = ard.getEndDate();

      runDateBudgetTotal = ard.getBudget().add(runDateBudgetTotal);

      if (ard.getBudget().doubleValue() > campaign.getBudget().doubleValue()) {
        throw new BadRequestParameterException("Budget is not equal to period budget");
      }
    }

    if (!runDateBudgetTotal.equals(new BigDecimal(0))
        && runDateBudgetTotal.compareTo(campaign.getBudget()) != 0) {
      throw new BadRequestParameterException("Budget is not equal to period budget");
    }

    return true;
  }

  private boolean validate(CampaignJson campaignJson) {

    if (campaignJson == null) {
      throw new BadRequestParameterException("Invalid campaign " + campaignJson);
    }

    if (campaignJson.getCampaignName() != null
        && campaignJson.getCampaignName().trim().length() > 0) {

      String cName = campaignJson.getCampaignName().trim().replaceAll("\\s+", " ");
      Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
      Matcher matcher = pattern.matcher(cName);
      if (matcher.find()) {
        campaignJson.setCampaignName(cName);
      } else {
        throw new BadRequestParameterException("Invalid Campaign Name : "
            + campaignJson.getCampaignName());
      }
    } else {
      throw new BadRequestParameterException("Campaign Name can not be null or blank");
    }

    if (null == campaignJson.getBudget()
        || campaignJson.getBudget().doubleValue() < new Double(0.01)
        || campaignJson.getBudget().doubleValue() > new Double(9999999999999d)) {
      throw new BadRequestParameterException("Invalid Budget : " + campaignJson.getBudget());
    }

    if (null != campaignJson.getCpmGoal()
        && (campaignJson.getCpmGoal().doubleValue() < new Double(0.01) || campaignJson
            .getCpmGoal().doubleValue() > new Double(9999999999999d))) {
      throw new BadRequestParameterException("Invalid CPM Goal : " + campaignJson.getCpmGoal());
    }

    if (campaignJson.getAdvertiserId() == null
        || campaignJson.getAdvertiserId().trim().length() == 0) {
      throw new BadRequestParameterException("Invalid Advertiser Id : "
          + campaignJson.getAdvertiserId());
    }

    // Validating run dates
    if (null != campaignJson.getRunDates() && !campaignJson.getRunDates().isEmpty()) {

      for (RunDateJson runDate : campaignJson.getRunDates()) {

        if (null == runDate.getStartDate() || null == runDate.getEndDate()
            || runDate.getStartDate().trim().length() == 0
            || runDate.getEndDate().trim().length() == 0) {
          throw new BadRequestParameterException("Date should not be blank");
        }
        if (!ValidationUtils.isDateValid(runDate.getStartDate().trim())) {
          throw new BadRequestParameterException("Invalid date : " + runDate.getStartDate());
        }

        if (!ValidationUtils.isDateValid(runDate.getEndDate().trim())) {
          throw new BadRequestParameterException("Invalid date : " + runDate.getEndDate());
        }

        if (null == runDate.getBudget()) {
          throw new BadRequestParameterException("Budget should not be blank");
        }

      }
    }
    return true;
  }


  @Override
  public CampaignJson converToJson(Campaign campaign) {
    CampaignJson campaignJson = null;
    DateFormat df = new SimpleDateFormat(DATE_FORMAT_ISO);

    if (campaign != null) {
      campaignJson = new CampaignJson();
      if (campaign.getUpdatedDate() != null) {
        Date lastUpdatedDate = campaign.getUpdatedDate();
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        String updatedAtISO = df.format(lastUpdatedDate);
        campaignJson.setUpdatedDate(updatedAtISO);

      }
      campaignJson.setAdvertiserId(campaign.getAdvertiserId());
      campaignJson.setCampaignName(campaign.getCampaignName());
      campaignJson.setBudget(campaign.getBudget());
      campaignJson.setCpmGoal(campaign.getCpmGoal());
      campaignJson.setCampaignId(campaign.getId());
      campaignJson.setCreatedBy(campaign.getCreatedBy());
      if (null != campaign.getCreatedDate()) {
        Date createdDate = campaign.getCreatedDate();
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        String createdATISO = df.format(createdDate);
        campaignJson.setCreatedDate(createdATISO);
      }
      campaignJson.setCampaignStatus(campaign.getCampaignStatus());

      List<RunDateJson> runDates = new ArrayList<RunDateJson>();
      if (campaign.getRunDates() != null) {
        runDates = new ArrayList<RunDateJson>();
        for (RunDate runDate : campaign.getRunDates()) {

          RunDateJson dateJson = new RunDateJson();
          if (null != runDate.getStartDate())
            dateJson.setStartDate(ValidationUtils.convertDateToString(DATE_FORMAT_YYYYMMDD,
                runDate.getStartDate()));
          if (null != runDate.getEndDate())
            dateJson.setEndDate(ValidationUtils.convertDateToString(DATE_FORMAT_YYYYMMDD,
                runDate.getEndDate()));
          dateJson.setBudget(runDate.getBudget());
          runDates.add(dateJson);
        }
      }

      campaignJson.setRunDates(runDates);

    }
    return campaignJson;
  }


   private Campaign convertFromJson(Campaign atc, CampaignJson campaignJson) {
    Campaign campaign = new Campaign();
    campaign.setAdvertiserId(campaignJson.getAdvertiserId());
    campaign.setCampaignName(campaignJson.getCampaignName().trim());
    campaign.setBudget(campaignJson.getBudget());
    campaign.setCpmGoal(campaignJson.getCpmGoal());

    List<RunDate> runDates = new ArrayList<RunDate>();
    for (RunDateJson jRunDate : campaignJson.getRunDates()) {
      RunDate aRunDate = new RunDate();
      if (null != jRunDate.getStartDate())
        aRunDate.setStartDate(ValidationUtils.convertStringToDate(DATE_FORMAT_YYYYMMDD,
            jRunDate.getStartDate()));
      if (null != jRunDate.getEndDate())
        aRunDate.setEndDate(ValidationUtils.convertStringToDate(DATE_FORMAT_YYYYMMDD,
            jRunDate.getEndDate()));
      aRunDate.setBudget(jRunDate.getBudget());
      runDates.add(aRunDate);
    }

    campaign.setRunDates(runDates);
    if (null != atc && null != atc.getTargetAudience()) {
      campaign.setTargetAudience(atc.getTargetAudience());
    }

    return campaign;
  }



}
