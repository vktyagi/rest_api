package com.demo.console.services;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public interface ElasticSearchWarapperService {

  public String searchMriSegment(String mriQuery, int offset, int limit) throws IOException,
      ParseException;

  public String searchAudience(String audienceQuery, Integer off, Integer lim) throws IOException,
      ParseException;

}