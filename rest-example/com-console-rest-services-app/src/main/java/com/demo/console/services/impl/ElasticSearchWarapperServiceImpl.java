package com.demo.console.services.impl;

import static com.demo.console.wrapper.search.service.impl.IndexPathConstants.ES_INDEX_PATH_AUDIENCE;
import static com.demo.console.wrapper.search.service.impl.IndexPathConstants.ES_INDEX_PATH_MRI;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.demo.console.services.ElasticSearchWarapperService;
import com.demo.console.utils.ValidationUtils;

@Service("elasticSearchService")
public class ElasticSearchWarapperServiceImpl implements ElasticSearchWarapperService {

    private static final Logger LOGGER = Logger.getLogger(ElasticSearchWarapperServiceImpl.class);
    Pattern p = Pattern.compile("[^A-Za-z0-9]");

    @Resource
    String elasticServerUrl;

    @Override
    public String searchMriSegment(String mriString, int offset, int limit) throws IOException, ParseException {
	LOGGER.info("Input mri string: " + mriString + " and limit: " + limit + " and offset: " + offset);

	StringBuilder elasticMriQuery = new StringBuilder("{\"from\" : " + offset + ", \"size\":" + limit + ",\"query\":{");
	if (ValidationUtils.isValidMRICode(mriString)) {
	    elasticMriQuery.append("\"match\" :{\"mriDictionaryId\":\"" + mriString + "\" }}}");
	} else {
	    elasticMriQuery.append("\"match\" :{\"mriLabel\":\"" + mriString + "\" }}}");
	}

	String elasticUrl = elasticServerUrl + ES_INDEX_PATH_MRI + "_search";
	return getElasticResponse(elasticUrl, elasticMriQuery.toString());
    }

    @Override
    public String searchAudience(String audienceQuery, Integer offset, Integer limit) throws IOException, ParseException {
	StringBuilder elasticAudienceQuery = new StringBuilder("{\"from\" : " + offset + ", \"size\":" + limit + ",\"query\":{");
	if (StringUtils.isNotBlank(audienceQuery)) {
	    elasticAudienceQuery.append("\"match\" :{\"name\":\"" + audienceQuery + "\" }}}");
	} else {
	    elasticAudienceQuery.append("\"match_all\" :{}},\"sort\": [{\"name\":{\"order\":\"asc\"}}]}");
	}
	String elasticUrl = elasticServerUrl + ES_INDEX_PATH_AUDIENCE + "_search";
	return getElasticResponse(elasticUrl, elasticAudienceQuery.toString());

    }

    public String getRankerResponse(String url) throws IOException, ParseException {
	LOGGER.info("Request input for Server URL : " + url);
	String isSuccess = "{\"error_description\" : \"Error occurs while using aws elastic\"}";
	try {
	    HttpGet httpGet = requestHeader(url.trim());
	    
	    HttpClient apacheClient = HttpClientBuilder.create().build();
	    HttpResponse response = apacheClient.execute(httpGet);
	    InputStream is = response.getEntity().getContent();
	    if (is != null) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = "";
		StringBuilder result = new StringBuilder();
		while ((line = reader.readLine()) != null && line.length() > 0) {
		    result.append(line);
		}
		return result.toString();
	    }
	} catch (Exception ex) {
	    LOGGER.error("Error while hit the elastic serach service : " + ex);
	}
	return isSuccess;
    }

    public HttpGet requestHeader(String restUrl) {
	HttpGet request = new HttpGet(restUrl);
	request.setHeader("Accept", "text/html,application/json,application/xml;q=0.9,*/*;q=0.8");
	request.setHeader("Accept-Language", "en-US,en;q=0.5");
	return request;
    }

    public String getElasticResponse(String url, String elasticQuery) throws IOException, ParseException {
	LOGGER.info("Request input for Server URL : " + url);
	String isSuccess = "{\"error_description\" : \"Error occurs while using aws elastic\"}";
	try {
	    HttpPost post = postHeader(url.trim());
	    StringEntity stEntity = new StringEntity(elasticQuery, "UTF-8");
	    stEntity.setContentType("application/json");
	    post.setEntity(stEntity);

	    HttpClient apacheClient = HttpClientBuilder.create().build();
	    HttpResponse response = apacheClient.execute(post);
	    
	    String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
	    InputStream is = new ByteArrayInputStream(responseString.getBytes());
	    
	    if (is != null) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = "";
		StringBuilder result = new StringBuilder();
		while ((line = reader.readLine()) != null && line.length() > 0) {
		    result.append(line);
		}
		return result.toString();
	    }
	} catch (Exception ex) {
	    LOGGER.error("Error while hit the elastic serach service : " + ex);
	}
	return isSuccess;
    }

    public HttpPost postHeader(String restUrl) {
	HttpPost post = new HttpPost(restUrl);
	post.setHeader("Accept-Language", "en-US,en;q=0.5");
	post.setHeader("Connection", "keep-alive");
	post.setHeader("Content-Type", "application/json");
	return post;
    }

}
