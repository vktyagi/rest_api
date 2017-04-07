package com.demo.console.wrapper.auth.wso2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.demo.console.wrapper.auth.AuthService;
import com.demo.console.wrapper.exception.NoRankerResponseReceivedException;

@SuppressWarnings("deprecation")
@Service("wso2AuthService")
public class WSO2AuthService implements AuthService {

  private static final Logger LOGGER = Logger.getLogger(WSO2AuthService.class);

  @Resource(name = "wso2-config")
  private WSO2Config wso2Config;

  private HttpClient httpClient;

  private String authenticate() throws IOException, ParseException {
    LOGGER.debug("Inside authenticate");
    httpClient = new DefaultHttpClient();

    HttpPost httpPost = new HttpPost(wso2Config.getAuthURL());
    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

    List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    postParameters = new ArrayList<NameValuePair>();
    postParameters.add(new BasicNameValuePair("grant_type", wso2Config.getGrantType()));
    postParameters.add(new BasicNameValuePair("username", wso2Config.getUsername()));
    postParameters.add(new BasicNameValuePair("password", wso2Config.getPassword()));
    postParameters.add(new BasicNameValuePair("client_id", wso2Config.getClientId()));
    postParameters.add(new BasicNameValuePair("client_secret", wso2Config.getClientSecret()));
    httpPost.setEntity(new UrlEncodedFormEntity(postParameters));

    LOGGER.debug("httppost :" + httpPost);
    HttpResponse httpResponse = httpClient.execute(httpPost);

    StatusLine sl = httpResponse.getStatusLine();

    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(httpResponse
        .getEntity().getContent()));
    String accessToken = (String) jsonObject.get("access_token");
   // String tokenType = (String) jsonObject.get("token_type");

    LOGGER.info("Access_token :" + accessToken + " StatusCode : " + sl.getStatusCode()
        + " ReasonPhrase :" + sl.getReasonPhrase());
    LOGGER.info(httpClient.toString());
    return "Bearer " + accessToken;
  }

  @Override
  public String getRankerorVAResults(String url) throws IOException, ParseException {
	LOGGER.info("Request input for getRankerResults Server URL : " + url);
	HttpGet httpGet = requestHeader(url.trim());
	HttpClient apacheClient = HttpClientBuilder.create().build();
	HttpResponse response = apacheClient.execute(httpGet);
	int responseCode = response.getStatusLine().getStatusCode();
	LOGGER.info("Response Code : " + responseCode + " for URL : " + url);
	if (responseCode == 200 || responseCode == 201) {
	    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    StringBuffer result = new StringBuffer();
	    String line = "";
	    while ((line = rd.readLine()) != null) {
		result.append(line);
	    }
	    rd.close();
	    return result.toString();

	} else {
	    throw new NoRankerResponseReceivedException("Invalid VA response received  for Server URL : " + url);
	}
  }

    
  @Override
  public String getRankerResponse(String url, String input) throws IOException, ParseException {
    LOGGER.info("Request input : " + input + " for Server URL : " + url);
    String isSuccess = "Error submitting to ranker";
    HttpPost post = postHeader(url.trim());
    post.setEntity(new StringEntity(input));
    HttpClient apacheClient = HttpClientBuilder.create().build();
    HttpResponse response = apacheClient.execute(post);
    int responseCode = response.getStatusLine().getStatusCode();
    LOGGER.info("Response Code : " + responseCode + " for URL : " + url);
    if (responseCode == 200 || responseCode == 201) {
      isSuccess = ("SUCCESS");
    } else {
      throw new NoRankerResponseReceivedException("Invalid Ranker Response received: " + isSuccess);
    }
    return isSuccess;
  }

  @Override
  public String deleteRankerCampaign(String url) throws IOException, ParseException {
    LOGGER.info(" for Server URL : " + url);
    String isSuccess = "Error submitting to ranker";
    HttpDelete delete = deleteHeader(url.trim());
    HttpClient apacheClient = HttpClientBuilder.create().build();
    HttpResponse response = apacheClient.execute(delete);
    int responseCode = response.getStatusLine().getStatusCode();
    LOGGER.info("Response Code : " + responseCode + " for URL : " + url);
    if (responseCode == 200 || responseCode == 201) {
      isSuccess = ("SUCCESS");
    }
    return isSuccess;
  }

  public HttpGet requestHeader(String restUrl) throws IOException, ParseException {
 	HttpGet request = new HttpGet(restUrl);
 	request.setHeader("Accept", "text/html,application/json,application/xml;q=0.9,*/*;q=0.8");
 	request.setHeader("Accept-Language", "en-US,en;q=0.5");
 	String authHeader = authenticate();
 	request.addHeader("Authorization", authHeader);
 	return request;
     }
  
  public HttpPost postHeader(String restUrl) throws IOException, ParseException {
    LOGGER.debug("Inside postHeader URL: " + restUrl);
    HttpPost post = new HttpPost(restUrl);
    String authHeader = authenticate();
    post.setHeader("Accept-Language", "en-US,en;q=0.5");
    post.setHeader("Connection", "keep-alive");
    post.setHeader("Content-Type", "application/json");
    post.addHeader("Authorization", authHeader);
    return post;
  }

  public HttpDelete deleteHeader(String restUrl) throws IOException, ParseException {
    LOGGER.debug("Inside deleteHeader URL: " + restUrl);
    HttpDelete delete = new HttpDelete(restUrl);
    String authHeader = authenticate();
    delete.setHeader("Accept-Language", "en-US,en;q=0.5");
    delete.setHeader("Connection", "keep-alive");
    delete.setHeader("Content-Type", "application/json");
    delete.addHeader("Authorization", authHeader);
    return delete;
  }
}
