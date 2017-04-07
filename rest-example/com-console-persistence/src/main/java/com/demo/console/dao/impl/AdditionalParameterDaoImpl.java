package com.demo.console.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.demo.console.dao.AdditionalParameterDao;
import com.demo.console.models.entities.AudienceParameterMaster;

@Repository("additionalParameterDao")
public class AdditionalParameterDaoImpl extends BaseDaoImpl<AudienceParameterMaster> implements
    AdditionalParameterDao {

  public AdditionalParameterDaoImpl(Class<AudienceParameterMaster> type) {
    super(type);
  }

  public AdditionalParameterDaoImpl() {
    super(AudienceParameterMaster.class);
  }
  
  @Override
  public List<AudienceParameterMaster> getParameterList(String parameterName){
      String query = "from AudienceParameterMaster where master_parameter_name = :parameterName";
      Map<String, Object> queryParam = new HashMap<String, Object>();
      queryParam.put("parameterName", parameterName);
      return findListByNameParameters(query, queryParam);
      
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public List<Object[]> getAllParameterList(){
      String queryStr = "SELECT m.master_parameter_name, m.master_parameter_name, m.master_parameter_name"
      	+ " FROM audience_parameter_master m"
         	+ " WHERE m.audience_paramete_name != 'N/A';";
      Query query = entityManager.createNativeQuery(queryStr);
      return query.getResultList();
  }
}
