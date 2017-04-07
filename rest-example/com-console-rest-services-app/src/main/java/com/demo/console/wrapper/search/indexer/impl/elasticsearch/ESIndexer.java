package com.demo.console.wrapper.search.indexer.impl.elasticsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.demo.console.wrapper.search.indexer.impl.BaseIndexer;

@Service("esIndexer")
public class ESIndexer extends BaseIndexer {

  private static final Logger LOGGER = Logger.getLogger(ESIndexer.class);
  static HttpClient httpClient = HttpClientBuilder.create().build();

  static String ES_RECORDS_COUNT_URL = "atv/_count";

  @Resource
  String elasticServerUrl;

  @Override
  public String createIndex(String indexPath, String data) {

    StringBuilder result = new StringBuilder();
    try {
      HttpPost httpPost = new HttpPost(elasticServerUrl + indexPath);
      httpPost.addHeader("Content-Type", "application/json");

      StringEntity stEntity =new StringEntity(data,"UTF-8");
      stEntity.setContentType("application/json");
      httpPost.setEntity(stEntity);
      
      HttpResponse httpResponse = httpClient.execute(httpPost);
      StatusLine sl = httpResponse.getStatusLine();
      LOGGER.debug(sl.getStatusCode() + " " + sl.getReasonPhrase());
      if (sl.getStatusCode() == 400) {
        LOGGER.info("Failed to load MRI index :: " + indexPath + ". MRI Request Data: " + data);
      }

      BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity()
          .getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        result.append(line);
      }
      rd.close();
    } catch (Exception e) {
      LOGGER.error(e);
      LOGGER.error("Error while creating elastic search indexing ", e);
    }

    return result.toString();

  }

  @Override
  public String deleteIndex(String indexPath) {
    StringBuilder result = new StringBuilder();
    try {
      HttpDelete httpDelete = new HttpDelete(elasticServerUrl + indexPath);
      HttpResponse httpResponse = httpClient.execute(httpDelete);

      BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity()
          .getContent()));
      String line = "";
      while ((line = rd.readLine()) != null) {
        result.append(line);
      }
      rd.close();
    } catch (ClientProtocolException e) {
      LOGGER.error(e);
    } catch (IOException e) {
      LOGGER.error(e);
    }
    return result.toString();
  }

  @Override
  public boolean isIndexed(String indexPath) {
    HttpHead head = new HttpHead(elasticServerUrl + indexPath);
    HttpResponse httpResponse = null;
    try {
      httpResponse = httpClient.execute(head);
      StatusLine sl = httpResponse.getStatusLine();
      LOGGER.info(sl.getStatusCode() + " " + sl.getReasonPhrase());
      if (sl.getStatusCode() == 200) {
        return true;
      }
    } catch (IOException e) {
      LOGGER.error(e);
    }
    return false;
  }

  @Override
  public long getTotalRecords() {
    long totalRecords = 0;
    try {
      HttpGet httpGet = new HttpGet(elasticServerUrl + ES_RECORDS_COUNT_URL);
      HttpResponse httpResponse = null;
      httpResponse = httpClient.execute(httpGet);
      StatusLine sl = httpResponse.getStatusLine();
      if (sl.getStatusCode() == 200) {
        BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity()
            .getContent()));
        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
          result.append(line);
        }
        rd.close();
        JSONParser j = new JSONParser();
        JSONObject o;
        o = (JSONObject) j.parse(result.toString());
        totalRecords = (long) o.get("count");

      }

    } catch (IOException e) {
      LOGGER.error("Exception while hit " + elasticServerUrl + ES_RECORDS_COUNT_URL, e);
    } catch (ParseException e) {
      LOGGER.error("Exception while parsing json from " + elasticServerUrl
          + ES_RECORDS_COUNT_URL, e);
    }
    LOGGER.info("Total Record : " + totalRecords);
    return totalRecords;
  }

  @Override
  public long getTotalRecords(String indexPath) {
    long totalRecords = 0;
    try {
      HttpGet httpGet = new HttpGet(elasticServerUrl + indexPath);
      HttpResponse httpResponse = null;
      httpResponse = httpClient.execute(httpGet);
      StatusLine sl = httpResponse.getStatusLine();
      if (sl.getStatusCode() == 200) {
        BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity()
            .getContent()));
        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
          result.append(line);
        }
        rd.close();
        JSONParser j = new JSONParser();
        JSONObject o;
        o = (JSONObject) j.parse(result.toString());
        totalRecords = (long) o.get("count");

      }

    } catch (IOException e) {
      LOGGER.error("Exception while hit " + elasticServerUrl + indexPath, e);
    } catch (ParseException e) {
      LOGGER.error("Exception while parsing json from " + elasticServerUrl + indexPath, e);
    }
    LOGGER.info("Total Record : " + totalRecords);
    return totalRecords;
  }

  @Override
  public void createIndexingOrMapping() {

    String elasticAnalizerJson = "{\"settings\":{\"analysis\":{\"filter\":{\"nGram_filter\":{\"type\":\"nGram\",\"min_gram\":1,\"max_gram\":20}},\"analyzer\":{\"nGram_analyzer\":{\"type\":\"custom\",\"tokenizer\":\"whitespace\",\"filter\":[\"lowercase\",\"asciifolding\",\"nGram_filter\"]},\"whitespace_analyzer\":{\"type\":\"custom\",\"tokenizer\":\"whitespace\",\"filter\":[\"lowercase\",\"asciifolding\"]}}}}}";
    try {
      HttpPost post = postHeader(elasticServerUrl + "/amp");
      post.setEntity(new StringEntity(elasticAnalizerJson));
      HttpClient apacheClient = HttpClientBuilder.create().build();
      HttpResponse response = apacheClient.execute(post);
      StatusLine sl = response.getStatusLine();
      if (sl.getStatusCode() == 200 || sl.getStatusCode() == 201) {
        LOGGER.info("Elastic indexing  created");
      }
    } catch (Exception ex) {
      LOGGER.error("Error while create elastic indexing : ", ex);
    }
  }

  public HttpPost postHeader(String restUrl) {
    HttpPost post = new HttpPost(restUrl);
    post.setHeader("Accept-Language", "en-US,en;q=0.5");
    post.setHeader("Connection", "keep-alive");
    post.setHeader("Content-Type", "application/json");
    return post;
  }

  @Override
  public void createElasticMriMapping(String mappingUrl) {
    String elasticMappingJson = "{\"mri\":{\"properties\":{\"mriLabel\":{\"type\":\"string\",\"index_analyzer\":\"nGram_analyzer\",\"search_analyzer\":\"whitespace_analyzer\"},\"mriDictionaryId\":{\"type\":\"string\",\"index_analyzer\":\"nGram_analyzer\",\"search_analyzer\":\"whitespace_analyzer\"}}}}";
    try {
      HttpPost post = postHeader(elasticServerUrl + mappingUrl);
      post.setEntity(new StringEntity(elasticMappingJson));
      HttpClient apacheClient = HttpClientBuilder.create().build();
      HttpResponse response = apacheClient.execute(post);
      StatusLine sl = response.getStatusLine();
      if (sl.getStatusCode() == 200 || sl.getStatusCode() == 201) {
        LOGGER.info("Elastic mri mapping created");
      }
    } catch (Exception ex) {
      LOGGER.error("Error while create elastic mapping : ", ex);
    }
  }

  @Override
  public void createElasticAudienceMapping(String mappingUrl) {
    String elasticMappingJson = "{ \"audience\": {\"properties\":{\"name\": {\"type\":\"string\",\"analyzer\":\"autocomplete\" },\"name\":{\"type\":\"string\",\"index_analyzer\":\"nGram_analyzer\",\"search_analyzer\" :\"whitespace_analyzer\" }}}}";
    try {
      HttpPost post = postHeader(elasticServerUrl + mappingUrl);
      post.setEntity(new StringEntity(elasticMappingJson));
      HttpClient apacheClient = HttpClientBuilder.create().build();
      HttpResponse response = apacheClient.execute(post);
      StatusLine sl = response.getStatusLine();
      if (sl.getStatusCode() == 200 || sl.getStatusCode() == 201) {
        LOGGER.info("Elastic audience mapping created");
      }
    } catch (Exception ex) {
      LOGGER.error("Error while create elastic mapping : ", ex);
    }
  }
}
