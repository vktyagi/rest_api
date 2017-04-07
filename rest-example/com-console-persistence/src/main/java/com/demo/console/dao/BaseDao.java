package com.demo.console.dao;

import java.util.List;

public interface BaseDao<T> {

  public T create(T t);

  public T findExactlyOne(Long id);

  public void delete(T t);

  public void deleteById(Long id);

  public List<T> findAll(String offset, String limit);

  public List<T> findAll();

  public T update(T t);

  public T save(T t);

  public int bulkSave(List<T> lt);

  public T findOneOrNull(final Long id);
  
  public T findResultsByParameters(final String queryString, Object queryParams);
}
