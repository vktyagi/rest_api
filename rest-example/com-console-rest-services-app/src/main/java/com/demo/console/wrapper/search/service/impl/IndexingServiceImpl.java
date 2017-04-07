package com.demo.console.wrapper.search.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.demo.console.wrapper.search.indexer.Indexer;
import com.demo.console.wrapper.search.service.IndexingService;

@Service("indexingService")
public class IndexingServiceImpl implements IndexingService {

  private static final Logger LOGGER = Logger.getLogger(IndexingServiceImpl.class);

  @Resource(name = "masterDataIndexingService")
  private MasterDataIndexingService masterDataIndexingService;

  @Resource(name = "esIndexer")
  private Indexer indexer;

  @Resource(name = "audienceDataIndexingService")
  private AudienceDataIndexingService audienceDataIndexingService;

  @Override
  public void initiateIndexing() {
    LOGGER.info("Going to initiate indexing for first time.");
    indexer.createIndexingOrMapping();
    audienceDataIndexingService.createAudienceIndeices();
    masterDataIndexingService.createMRIIndeices();
  }

}
