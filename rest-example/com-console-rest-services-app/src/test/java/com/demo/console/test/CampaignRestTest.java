package com.demo.console.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.demo.console.test.BaseTestCase;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CampaignRestTest extends BaseTestCase {

  private Double campaignId;

  @Test
  public void testCampaignCRUD() throws Exception {
    testCreateCampaign();
    testGetCampaign();
    testUpdateCampaign();
    testDeleteCampaign();
  }

  // @Test
  public void testCreateCampaign() throws Exception {

    String serviceUrl = "/campaigninfo/"; //
    // Applebee's\
    // {\"startDate\":\"2016-09-11\",\"endDate\":\"2016-09-20\",\"budget\":100,\"budgetPercent\":50},{\"startDate\":\"2016-09-21\",\"endDate\":\"2016-09-23\",\"budget\":50,\"budgetPercent\":25},{\"startDate\":\"2016-09-24\",\"endDate\":\"2016-09-30\",\"budget\":50,\"budgetPercent\":25}
    String input_json = "{\"campaignName\":\"2015 TV Campaign 1\",\"advertiserId\":\"abc\",\"cpmGoal\":8.75,\"budget\":200,\"ampRunDates\":[]}";

    MvcResult mvcreturn = this.mockMvc
        .perform(
            post(serviceUrl).contentType(jsonContentType).content(input_json)
                .accept(jsonContentType)).andExpect(status().isOk()).andDo(print()).andReturn();
    // .andExpect(jsonPath("$meta.info.campaign_id", is("1"))).andReturn();

    System.out.println("\n---------------" + this.getClass().getName() + "-----------------\n");
    mvcreturn.getResponse().getWriter().println();
    System.out.println(mvcreturn.getResponse().getContentAsString());
    System.out
        .println("---------------------------------------------------------------------------");

    Gson gson = new Gson();
    campaignId = (Double) gson
        .fromJson(mvcreturn.getResponse().getContentAsString(), HashMap.class).get("campaignId");
    Assert.assertNotNull("No response received", campaignId);

  }

  @Test
  public void testCreateCampaignFail() throws Exception {

    String serviceUrl = "/campaigninfo/";
    // Applebee's\
    String input_json = "{\"campaignName\":\"2015 TV Campaign 1\",\"advertiserId\":\"abc\",\"cpmGoal\":8.75,\"budget\":200,\"ampRunDates\":[{\"startDate\":\"2015-08-1x\",\"endDate\":\"2015-08-20\",\"budget\":100},{\"startDate\":\"2015-08-21\",\"endDate\":\"2015-08-23\",\"budget\":50},{\"startDate\":\"2015-08-24\",\"endDate\":\"2015-08-30\",\"budget\":50}]}";

    MvcResult mvcreturn = this.mockMvc
        .perform(
            post(serviceUrl).contentType(jsonContentType).content(input_json)
                .accept(jsonContentType)).andExpect(status().isBadRequest()).andDo(print())
        .andReturn();
    // .andExpect(jsonPath("$meta.info.campaign_id", is("1"))).andReturn();

    System.out.println("\n---------------" + this.getClass().getName() + "-----------------\n");
    mvcreturn.getResponse().getWriter().println();
    System.out.println(mvcreturn.getResponse().getContentAsString());
    System.out
        .println("---------------------------------------------------------------------------");

  }

  // @Test
  public void testGetCampaign() throws Exception {
    String serviceUrl = "/campaigninfo/campaignid/" + campaignId.longValue();
    MvcResult mvcreturn = this.mockMvc
        .perform(get(serviceUrl).contentType(jsonContentType).accept(jsonContentType))
        .andExpect(status().isOk()).andDo(print()).andReturn();

    System.out.println("\n---------------" + this.getClass().getName() + "-----------------\n");
    mvcreturn.getResponse().getWriter().println();
    System.out.println(mvcreturn.getResponse().getContentAsString());
    System.out
        .println("---------------------------------------------------------------------------");

    JsonElement jsonElement = new JsonParser().parse(mvcreturn.getResponse().getContentAsString());
    JsonObject campaignInfoObj = jsonElement.getAsJsonObject().getAsJsonObject("campaigninfo");
    Assert.assertTrue("Test Campaign not found ", campaignInfoObj.get("campaignName").getAsString()
        .equals("2015 TV Campaign 1"));

  }

  public void testUpdateCampaign() throws Exception {

    String serviceUrl = "/campaigninfo/campaignid/" + campaignId.longValue();
    // Applebee's\
    // {\"startDate\":\"2016-09-11\",\"endDate\":\"2016-09-20\",\"budget\":100,\"budgetPercent\":50},{\"startDate\":\"2016-09-21\",\"endDate\":\"2016-09-23\",\"budget\":50,\"budgetPercent\":25},{\"startDate\":\"2016-09-24\",\"endDate\":\"2016-09-30\",\"budget\":50,\"budgetPercent\":25}
    String input_json = "{\"campaignName\":\"2015 TV Campaign 1\",\"advertiserId\":\"abc\",\"cpmGoal\":8.75,\"budget\":150,\"ampRunDates\":[]}";

    MvcResult mvcreturn = this.mockMvc
        .perform(
            put(serviceUrl).contentType(jsonContentType).content(input_json)
                .accept(jsonContentType)).andExpect(status().isOk()).andDo(print()).andReturn();
    // .andExpect(jsonPath("$meta.info.campaign_id", is("1"))).andReturn();

    System.out.println("\n---------------" + this.getClass().getName() + "-----------------\n");
    mvcreturn.getResponse().getWriter().println();
    System.out.println(mvcreturn.getResponse().getContentAsString());
    System.out
        .println("---------------------------------------------------------------------------");

    Gson gson = new Gson();
    campaignId = (Double) gson
        .fromJson(mvcreturn.getResponse().getContentAsString(), HashMap.class).get("campaignId");
    Assert.assertNotNull("No response received", campaignId);

  }

  public void testDeleteCampaign() throws Exception {

    String serviceUrl = "/campaigninfo/campaignid/" + campaignId.longValue();
    // Applebee's\
    // {\"startDate\":\"2016-09-11\",\"endDate\":\"2016-09-20\",\"budget\":100,\"budgetPercent\":50},{\"startDate\":\"2016-09-21\",\"endDate\":\"2016-09-23\",\"budget\":50,\"budgetPercent\":25},{\"startDate\":\"2016-09-24\",\"endDate\":\"2016-09-30\",\"budget\":50,\"budgetPercent\":25}

    MvcResult mvcreturn = this.mockMvc
        .perform(delete(serviceUrl).contentType(jsonContentType).accept(jsonContentType))
        .andExpect(status().isOk()).andDo(print()).andReturn();
    // .andExpect(jsonPath("$meta.info.campaign_id", is("1"))).andReturn();

    System.out.println("\n---------------" + this.getClass().getName() + "-----------------\n");
    mvcreturn.getResponse().getWriter().println();
    System.out.println(mvcreturn.getResponse().getContentAsString());
    System.out
        .println("---------------------------------------------------------------------------");

    Gson gson = new Gson();
    Assert.assertEquals("SUCCESS",
        gson.fromJson(mvcreturn.getResponse().getContentAsString(), HashMap.class).get("response"));

  }

}
