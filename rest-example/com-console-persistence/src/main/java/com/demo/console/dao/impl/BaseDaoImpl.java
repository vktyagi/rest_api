package com.demo.console.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.console.dao.BaseDao;
import com.demo.console.models.entities.BaseDaoObject;

@Transactional(value = "sessionTxm")
public class BaseDaoImpl<T extends BaseDaoObject> implements BaseDao<T> {

  private static final Logger LOGGER = Logger.getLogger(BaseDaoImpl.class);

  @PersistenceContext(unitName = "persistanceUnit")
  protected EntityManager entityManager;

  protected final Class<T> entityClass;

  public BaseDaoImpl(Class<T> type) {
    this.entityClass = type;
  }

  @Override
  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRED)
  public T update(final T entity) {

    return entityManager.merge(entity);

  }

  @Override
  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRED)
  public void delete(final T entity) {

    entityManager.remove(entity);

  }

  @Override
  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRED)
  public void deleteById(final Long entityId) {

    final T entity = entityManager.getReference(entityClass, entityId);

    entityManager.remove(entity);
  }

  @Override
  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public T findOneOrNull(final Long id) {

    return entityManager.find(entityClass, id);
  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public T findOneOrNullByStringKey(final String id) {

    return entityManager.find(entityClass, id);

  }

  @Override
  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public T findExactlyOne(final Long id) {

    T result = findOneOrNull(id);

    if (result == null) {
      String msg = String.format("Unable to find %s with id=%d", entityClass.getSimpleName(), id);
      throw new NoResultException(msg);
    }
    return result;
  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public T findExactlyOne(final String query, Object... queryParams) {
    TypedQuery<T> q = entityManager.createQuery(query, entityClass);
    for (int i = 0; i < queryParams.length; i++) {
      q.setParameter(i + 1, queryParams[i]);
    }
    return q.getSingleResult();

  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public Object findExactlyOneAnyObject(final String query, Object... queryParams) {

    Query q = entityManager.createQuery(query);
    for (int i = 0; i < queryParams.length; i++) {
      q.setParameter(i + 1, queryParams[i]);
    }
    return  q.getSingleResult();
  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public Object findExactlyOneAnyObject(final String query) {

    Query q = entityManager.createQuery(query);

    return q.getSingleResult();
  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public List<T> findList(final String query, Map<String, Object> queryParams) {

    TypedQuery<T> q = createTypedQueryWithQueryParameters(query, queryParams);

    return q.getResultList();
  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public List<T> findListByOffSetAndLimit(Integer offset, Integer limit, final String query,
      Map<String, Object> queryParams) {
    TypedQuery<T> q = createTypedQueryWithQueryParameters(query, queryParams);

    q.setFirstResult(offset);
    if (limit > 0) {
      q.setMaxResults(limit);
    }
    return q.getResultList();
  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public List<T> findListByNameParameters(final String query, Map<String, Object> queryParams) {

    TypedQuery<T> q = createTypedQueryWithQueryParameters(query, queryParams);
    List<T> results = new ArrayList<T>();
    try {
      results = q.getResultList();

    } catch (NoResultException e) {
      LOGGER.debug(e);
    }
    return results;
  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public List<T> findListByQuery(final String query) {

    TypedQuery<T> q = entityManager.createQuery(query, entityClass);

    List<T> results = new ArrayList<T>();
    try {
      results = q.getResultList();

    } catch (NoResultException e) {
      LOGGER.debug(e);
    }
    return results;

  }

  @SuppressWarnings("unchecked")
  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public List<T> findList(Query query) {

    return query.getResultList();
  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public List<T> findAllWithParams(String query, Integer offset, Integer limit) {
    TypedQuery<T> q = entityManager.createQuery(query, entityClass);
    if (offset > 0) {
      q.setFirstResult(offset);
    }

    if (limit > 0) {
      q.setMaxResults(limit);
    }
    return q.getResultList();
  }

  @Override
  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public List<T> findAll(String offset, String limit) {
    CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(
        entityClass);
    criteriaQuery.from(entityClass);
    TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
    if (offset != null) {
      query.setFirstResult(Integer.parseInt(offset));
    }

    if (limit != null) {
      query.setMaxResults(Integer.parseInt(limit));
    }
    return query.getResultList();
  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public boolean isManaged(T t) {
    return entityManager.contains(t);
  }

  @Override
  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRED)
  public T save(T t) {

    if (null != t) {
      if ((null == t.getId()) || (t.getId() == 0L)) {
        entityManager.persist(t);
        entityManager.flush();
      } else {
        return entityManager.merge(t);
      }
    }

    return t;
  }


  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRED)
  public T saveWithStringKey(T t) {

    if (t != null) {
      entityManager.persist(t);
    } else {
      return entityManager.merge(t);
    }

    return t;
  }

  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRED)
  public T updateWithoutSelect(final T entity) {
    Object delegate = entityManager.getDelegate();
    if (!(delegate instanceof Session)) {
      throw new UnsupportedOperationException(
          "updateWithoutSelect is only implemented for Hibernate-JPA");
    }
    Session session = (Session) delegate;
    session.saveOrUpdate(entity);
    return entity;
  }

  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRED)
  public int updateOrDeleteByQueryString(String queryStr, Object... queryParams) {
    Query query = entityManager.createQuery(queryStr);
    for (int i = 0; i < queryParams.length; i++) {
      query.setParameter(i + 1, queryParams[i]);
    }
    return query.executeUpdate();
  }

  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRED)
  public int updateOrDeleteByQueryNamedParams(String queryStr, Object queryParams) {
    Query q = entityManager.createQuery(queryStr);
    if (queryParams instanceof Map) {
      @SuppressWarnings("unchecked")
      Map<String, Object> queryParamsMap = (Map<String, Object>) queryParams;
      if (null == queryParamsMap || queryParamsMap.isEmpty()) {
        return 0;
      }
      Set<String> queryParameterSet = queryParamsMap.keySet();
      if (null == queryParameterSet || queryParameterSet.isEmpty()) {
        return 0;
      }
      for (Object keyObject : queryParameterSet) {
        if (null == keyObject) {
          continue;
        }
        String key = (String) keyObject;
        Object value = queryParamsMap.get(key);
        q.setParameter(key, value);
      }
    }
    return q.executeUpdate();
  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public T findExactlyOneOrNullByNamedParameters(final String query, Map<String, Object> queryParams) {

    TypedQuery<T> q = createTypedQueryWithQueryParameters(query, queryParams);

    try {
      T result = q.getSingleResult();
      if (null != result) {
        return result;
      } else {
        return null;
      }
    } catch (NoResultException e) {
      LOGGER.debug(e);
      return null;
    }
  }

  @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
  public T findOnlyOneOrNullByNamedParameters(final String query, Map<String, Object> queryParams) {

    TypedQuery<T> q = createTypedQueryWithQueryParameters(query, queryParams);

    try {
      List<T> resultList = new ArrayList<T>();
      T result = null;
      resultList = q.getResultList();
      if (!resultList.isEmpty()) {
        for (int index = 0; index < 1; index++) {
          result = resultList.get(index);
        }
        return result;
      } else {
        return null;
      }
    } catch (NoResultException e) {
      LOGGER.debug(e);
      return null;
    }
  }

  public Session getSession() {

    return (Session) entityManager.getDelegate();

  }

  @Override
  public List<T> findAll() {
    CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(
        entityClass);
    criteriaQuery.from(entityClass);
    TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
    return query.getResultList();
  }

  @Override
  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRED)
  public T create(T t) {

    entityManager.persist(t);

    return t;
  }

  @Override
  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRED)
  public int bulkSave(List<T> lt) {
    int count = lt.size();
    for (int i = 0; i < count; i++) {
      T t = lt.get(i);
      entityManager.persist(t);
      if (i % 100 == 0) {
        entityManager.flush();
        entityManager.clear();
      }
    }
    return count;
  }

  @SuppressWarnings("unchecked")
  protected List<Object[]> findListByNativeQuery(String query) {
    Query q = entityManager.createNativeQuery(query);
    return q.getResultList();
  }

  private TypedQuery<T> createTypedQueryWithQueryParameters(String query,
      Map<String, Object> queryParams) {

    TypedQuery<T> q = entityManager.createQuery(query, entityClass);

    if (queryParams == null || queryParams.isEmpty()) {
      throw new javax.persistence.PersistenceException("queryParams are null or empty");
    }

    for (Object keyObject : queryParams.keySet()) {
      if (null == keyObject) {
        continue;
      }
      String key = (String) keyObject;
      Object value = queryParams.get(key);
      q.setParameter(key, value);
    }
    return q;
  }
  
    @SuppressWarnings("unchecked")
    @Transactional(value = "sessionTxm", readOnly = true, propagation = Propagation.SUPPORTS)
    public T findResultsByParameters(final String queryString, Object queryParams) {
	Query query = entityManager.createQuery(queryString);
	if (queryParams instanceof Map) {
	    Map<String, Object> queryParamsMap = (Map<String, Object>) queryParams;
	    if (queryParamsMap == null || queryParamsMap.isEmpty()) {
		return null;
	    }
	    Set<String> queryParameterSet = queryParamsMap.keySet();
	    if (queryParameterSet == null || queryParameterSet.isEmpty()) {
		return null;
	    }
	    for (Object keyObject : queryParameterSet) {
		if (keyObject == null) {
		    continue;
		}
		String key = (String) keyObject;
		Object value = queryParamsMap.get(key);
		query.setParameter(key, value);
	    }
	}
	T result = null;
	try {
	    Object obj = query.getSingleResult();
	    result = (T) obj;
	    return result;
	} catch (Exception e) {
	    LOGGER.error("Exception while finding record count:", e);
	}
	return result;
    }
}
