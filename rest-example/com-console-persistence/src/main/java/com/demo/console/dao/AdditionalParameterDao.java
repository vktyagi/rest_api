package com.demo.console.dao;

import java.util.List;

import com.demo.console.models.entities.AudienceParameterMaster;

public interface AdditionalParameterDao extends BaseDao<AudienceParameterMaster> {

    List<AudienceParameterMaster> getParameterList(String parameterName);

    List<Object[]> getAllParameterList(); 

}
