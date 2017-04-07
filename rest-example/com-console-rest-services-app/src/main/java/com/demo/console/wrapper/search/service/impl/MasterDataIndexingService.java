package com.demo.console.wrapper.search.service.impl;

import static com.demo.console.wrapper.search.service.impl.IndexPathConstants.ES_INDEX_PATH_MRI;
import static com.demo.console.wrapper.search.service.impl.IndexPathConstants.ES_MAPPING_PATH_MRI;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.demo.console.models.data.MriParameterJson;
import com.demo.console.services.MasterDataService;
import com.demo.console.wrapper.search.indexer.Indexer;
import com.google.gson.Gson;

@Service("masterDataIndexingService")
public class MasterDataIndexingService {

  private static final Logger LOGGER = Logger.getLogger(MasterDataIndexingService.class);

  @Resource(name = "esIndexer")
  private Indexer indexer;

  @Resource(name = "masterDataService")
  private MasterDataService masterDataService;

  public void createMRIIndeices() {
    long totalRecords = 0;
    if (indexer.isIndexed(ES_INDEX_PATH_MRI)) {
      totalRecords = indexer.getTotalRecords(ES_INDEX_PATH_MRI + "_count");
      indexer.deleteIndex(ES_INDEX_PATH_MRI);
      LOGGER.info("Deleted #" + totalRecords + " existing MRI indexes from ES for sync.");
      totalRecords = 0;
    }
    indexer.createElasticMriMapping(ES_MAPPING_PATH_MRI);
    List<MriParameterJson> mriRecords = masterDataService.getAllMriParameterList(totalRecords);

    if (totalRecords != mriRecords.size()) {
      Gson gson = new Gson();

      for (MriParameterJson mriParameterJson : mriRecords) {
        indexer.createIndex(ES_INDEX_PATH_MRI + mriParameterJson.getMriDictionaryId(),
            gson.toJson(mriParameterJson));
      }
      LOGGER.info("Total mri indexed records : " + (mriRecords.size() - totalRecords));    
    } else {
      LOGGER.info("All MRI indexes are up-to-date");
    }
  }

}
