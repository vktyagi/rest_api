package com.demo.console.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.demo.console.dao.MriParameterDao;
import com.demo.console.models.entities.MriParameterMaster;

@Repository("mriParameterDaoDao")
public class MriParameterDaoImpl extends BaseDaoImpl<MriParameterMaster> implements
    MriParameterDao {

  public MriParameterDaoImpl(Class<MriParameterMaster> type) {
    super(type);
  }

  public MriParameterDaoImpl() {
    super(MriParameterMaster.class);
  }

  @Override
  public List<MriParameterMaster> getMriSegmentabels(List<String> mriList) {
    String query = "from MriParameterMaster where mri_dictionary_id in (:mriDictionaryId)";
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("mriDictionaryId", mriList);
    return findListByNameParameters(query, queryParams);
  }

  @Override
  public List<MriParameterMaster> getMriLabelList(long offset) {

    StringBuilder q = new StringBuilder("from MriParameterMaster");
    TypedQuery<MriParameterMaster> query = entityManager.createQuery(q.toString(),
        MriParameterMaster.class);

    query.setFirstResult((int) offset);
    return query.getResultList();
  }
}
