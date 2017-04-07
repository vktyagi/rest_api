package com.demo.console.dao;

import java.util.List;

import com.demo.console.models.entities.MriParameterMaster;

public interface MriParameterDao extends BaseDao<MriParameterMaster> {

    List<MriParameterMaster> getMriSegmentabels(List<String> mriList);

    List<MriParameterMaster> getMriLabelList(long offset);

}
