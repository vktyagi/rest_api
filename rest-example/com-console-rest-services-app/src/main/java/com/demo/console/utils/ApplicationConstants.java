package com.demo.console.utils;

public class ApplicationConstants {

  public static final String CONTENT_TYPE = "application/json;charset=UTF-8";
  public static final String USER_NAME = "userName";

  public static final String BASEPLAN_FILE_NAME_PART_CID = "_cid_";

  public static final String REQUEST_DATE_FORMAT = "yyyy-MM-dd";

  public static final String MSG_INVALID_CAMPAIGN_ID = "Invalid CampaignId : ";
  public static final String MSG_INVALID_CAMPAIGN_TYPE = "Invalid CampaignId Type : ";
  public static final String MSG_BASEPLAN_ERR_ROW = "' in row = '";
  public static final String MSG_RANKER_NO_DATA_FOUND = "No Data found for CampaignId :";

  public static final String JSON_RESPONSE_ATTRIBUTE = "response";
  public static final String JSON_ATTR_CAMPAIGN_INFO = "campaigninfo";
  public static final String JSON_ATTR_INVENTORY = "inventory";
  public static final String JSON_ATTR_RESULT_TYPE = "resultType";

  public static final String JSON_RESP_SUCCESS = "SUCCESS";

  public static final String JSON_ATTR_AUDIENCE_MALE = "males";
  public static final String JSON_ATTR_AUDIENCE_FEMALE = "females";

  public static final String TEMPLATE_PREFIX = "base_plan_templates";
  public static final String BASE_PLAN_URL_TEMPLATE_FILE = "base_plan_template.csv";
  public static final String CSV_CONTENT_TYPE="text/csv";
  
  public final static String HOUSE_HOLD_INCOME_KEY = "HOUSEHOLD_INCOME";
  public final static String RANKER_RESULTS = "RANKER_RESULTS";
  public final static String VALUE_ANALYSIS = "VALUE_ANALYSIS";
  
  public final static String SUCCESS = "SUCCESS";
  public final static String FAILURE = "FAILURE";
  
  private ApplicationConstants() {
  }
}
