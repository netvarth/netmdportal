package com.nv.youNeverWait.util.filter.core;

import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import com.nv.youNeverWait.pl.dao.FilterDao;


public interface QueryBuilder{
	public void addFilter(Filter filter);
	public void init(FilterDao filterDao);
	public <T> TypedQuery<T> buildQuery(boolean asc,int start,int end);
	public <T> TypedQuery<T> buildSearchQuery(boolean asc,int start,int end);
	public <T> List<T> executeQuery(TypedQuery<T> q);
	public CriteriaBuilder getCriteriaBuilder();
	public  <T> CriteriaQuery<T> getCriteriaQuery();
	public <T> Root<T> getRoot();
	public Map<String,Path<Object>> getPathReferenceMap();
	public <T> Map<String,Join<T,?>> getReferenceMap();
	public Long getCount();
	public Long getSearchCount();
	
}
