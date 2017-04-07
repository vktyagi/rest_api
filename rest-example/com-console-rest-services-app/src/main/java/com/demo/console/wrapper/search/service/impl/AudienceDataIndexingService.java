package com.demo.console.wrapper.search.service.impl;

import static com.demo.console.wrapper.search.service.impl.IndexPathConstants.ES_INDEX_PATH_AUDIENCE;
import static com.demo.console.wrapper.search.service.impl.IndexPathConstants.ES_MAPPING_PATH_AUDIENCE;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.springframework.stereotype.Service;

import com.demo.console.models.json.TargetAudience;
import com.demo.console.wrapper.search.indexer.Indexer;
import com.google.gson.Gson;

@Service("audienceDataIndexingService")
public class AudienceDataIndexingService {
  private static final Logger LOGGER = Logger.getLogger(AudienceDataIndexingService.class);

  @Resource(name = "esIndexer")
  private Indexer indexer;

 /* @Resource(name = "audienceService")
  private AudienceService audienceService;*/

  @Resource
  String elasticServerUrl;

    public void createAudienceIndeices() {
	Gson gson = new Gson();
	if (indexer.isIndexed(ES_INDEX_PATH_AUDIENCE)) {
	    indexer.deleteIndex(ES_INDEX_PATH_AUDIENCE);
	}
	indexer.createElasticAudienceMapping(ES_MAPPING_PATH_AUDIENCE);
	//List<TargetAudience> targetAudienceList = audienceService.getAllTargetAudience(false, 0, 0);
	List<TargetAudience> targetAudienceList = new ArrayList<TargetAudience>();
	LOGGER.info("Target Audience Size : " + targetAudienceList.size());
	for (TargetAudience targetAudience : targetAudienceList) {
	    indexer.createIndex(ES_INDEX_PATH_AUDIENCE + targetAudience.getAudienceId(), gson.toJson(targetAudience));
	}
	LOGGER.info("Target Audience Indexing completed: ");
    }

  public String createAudienceIndeces(TargetAudience targetAudience) {
    Gson gson = new Gson();
    TargetAudience audience = new TargetAudience();
    audience.setAudienceId(targetAudience.getAudienceId());
    audience.setName(targetAudience.getName());

    return indexer.createIndex(ES_INDEX_PATH_AUDIENCE + targetAudience.getAudienceId(),
        gson.toJson(audience));
  }

  public void deleteAudienceIndeces(long audienceId) {
    indexer.deleteIndex(ES_INDEX_PATH_AUDIENCE + audienceId);
  }

  public boolean isIndexed(long audienceId) {
    return indexer.isIndexed(ES_INDEX_PATH_AUDIENCE + audienceId);
  }

  public void createBulkAudienceIndex() throws IOException {
    // on startup
    Node node = nodeBuilder().clusterName(elasticServerUrl).node();
    Client client = node.client();

    try {
      BulkRequestBuilder bulkRequest = client.prepareBulk();

    //List<TargetAudience> targetAudienceList = audienceService.getAllTargetAudience(false, 0, 0);
    	List<TargetAudience> targetAudienceList = new ArrayList<TargetAudience>();
      LOGGER.info("Target Audience Size : " + targetAudienceList.size());

      for (TargetAudience targetAudience : targetAudienceList) {
        bulkRequest.add(client.prepareIndex("audiences", "audience",
            targetAudience.getAudienceId() + "")
            .setSource(
                jsonBuilder().startObject().field("audienceName", targetAudience.getName())
                    .endObject()));
      }
      LOGGER.info("Sending  bulk indexing for audience...");
      BulkResponse bulkResponse = bulkRequest.execute().actionGet();
      if (bulkResponse.hasFailures()) {
        LOGGER.info("Error indexing Audience........");
      }
    } finally {
      node.close();
    }
  }

}
