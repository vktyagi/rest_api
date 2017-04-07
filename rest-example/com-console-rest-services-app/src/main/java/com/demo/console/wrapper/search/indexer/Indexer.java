package com.demo.console.wrapper.search.indexer;

public interface Indexer {

  public String createIndex(String index, String data);

  public boolean isIndexed(String indexPath);

  public long getTotalRecords();

  public long getTotalRecords(String indexPath);

  public String deleteIndex(String indexPath);

  public void createIndexingOrMapping();

  public void createElasticMriMapping(String mappingUrl);

  public void createElasticAudienceMapping(String mappingUrl);

}
