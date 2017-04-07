package com.demo.console.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.demo.console.dao.CampaignDao;
import com.demo.console.models.entities.Campaign;

@Repository("campaignDao")
public class CampaignDaoImpl extends BaseDaoImpl<Campaign> implements CampaignDao {

  public CampaignDaoImpl() {
    super(Campaign.class);
  }

  @Override
  public Campaign findCampaignByName(String campaignName) {
    String query = "from Campaign where campaignName = :campaignName";
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("campaignName", campaignName);

    return findExactlyOneOrNullByNamedParameters(query, queryParams);
  }

  @Override
  public Campaign getCampaignById(Long id) {

    String query = "from Campaign where campaignId = :campaignId";
    Map<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("campaignId", id);

    return findExactlyOneOrNullByNamedParameters(query, queryParams);
  }

  @Override
  public Campaign save(Campaign campaign) {

    Campaign tempCampaign = findExactlyOne(campaign.getId());

    tempCampaign.setAdvertiserId(campaign.getAdvertiserId());
    tempCampaign.setTargetAudience(campaign.getTargetAudience());
    tempCampaign.setTargetAudience(campaign.getTargetAudience());
    tempCampaign.setBudget(campaign.getBudget());
    tempCampaign.setCampaignName(campaign.getCampaignName());
    tempCampaign.setCpmGoal(campaign.getCpmGoal());
    tempCampaign.setCampaignStatus(campaign.getCampaignStatus());
    tempCampaign.setUpdatedBy(campaign.getUpdatedBy());
    tempCampaign.setUpdatedDate(campaign.getUpdatedDate());

    return create(tempCampaign);

  }

  @Override
  public List<Campaign> getCampaignByStatus(String status, Integer offset, Integer limit,
      String sortOn, String order) {
    StringBuilder q = new StringBuilder("from Campaign where UPPER(campaignStatus) = '"
        + status + "'");
    if ("name".equalsIgnoreCase(sortOn)) {
      q.append(" order by campaign_name");
    } else if ("created_date".equalsIgnoreCase(sortOn)) {
      q.append(" order by created_date");
    } else if ("created_by".equalsIgnoreCase(sortOn)) {
      q.append(" order by created_by");
    } else if ("advertiser".equalsIgnoreCase(sortOn)) {
      q.append(" order by advertiser_id");
    }

    if ("asc".equalsIgnoreCase(order)) {
      q.append(" asc");
    } else if ("desc".equalsIgnoreCase(order)) {
      q.append(" desc");
    }

    TypedQuery<Campaign> query = entityManager.createQuery(q.toString(),
        Campaign.class);

    query.setFirstResult(offset);
    if (limit > 0) {
      query.setMaxResults(limit);
    }

    return query.getResultList();
  }

}
