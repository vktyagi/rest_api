package com.demo.console.services;

import java.util.List;

import com.demo.console.models.data.MriParameterJson;

public interface MasterDataService {

  List<MriParameterJson> getAllMriParameterList(long offset);
}
