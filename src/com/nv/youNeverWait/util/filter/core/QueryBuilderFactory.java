/**
 * QueryBuilderFactory.java
 *
 * Aug 17, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.util.filter.core;

import java.util.HashMap;
import java.util.Map;

import com.nv.youNeverWait.pl.dao.FilterDao;


public class QueryBuilderFactory {

	private FilterDao filterDao;
	private  Map<String,String> queryBuilderMap = new HashMap<String, String>() ;

	public QueryBuilder getQueryBuilder(String rootName){
		QueryBuilder queryBuilder = null;
		try {
			String builderName = queryBuilderMap.get(rootName);
			Class classObj= Class.forName(builderName);
			queryBuilder=  (QueryBuilder) classObj.newInstance();
			queryBuilder.init(filterDao);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return	queryBuilder;
	}
	/**
	 * @return the filterDao
	 */
	public FilterDao getFilterDao() {
		return filterDao;
	}
	/**
	 * @param filterDao the filterDao to set
	 */
	public void setFilterDao(FilterDao filterDao) {
		this.filterDao = filterDao;
	}
	/**
	 * @return the queryBuilderMap
	 */
	public Map<String, String> getQueryBuilderMap() {
		return queryBuilderMap;
	}
	/**
	 * @param queryBuilderMap the queryBuilderMap to set
	 */
	public void setQueryBuilderMap(Map<String, String> queryBuilderMap) {
		this.queryBuilderMap = queryBuilderMap;
	}

}
