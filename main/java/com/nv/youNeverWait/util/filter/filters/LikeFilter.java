/**
 * LikeFilter.java
 *
 * Jan 23,2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.util.filter.filters;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import com.nv.youNeverWait.util.filter.core.Criteria;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;



public class LikeFilter implements com.nv.youNeverWait.util.filter.core.Filter{
	private String name;
	private String value;

	public LikeFilter(){

	}
	public LikeFilter(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * Get predicate
	 */
	@Override
	public Criteria getPredicate(QueryBuilder queryBuilder,Property property) {

		Predicate predicate = null;
		Criteria criteria = new Criteria();
		if(value==null){
			return criteria;	
		}
		if(queryBuilder.getPathReferenceMap().get(property.getPathReferenceName())!=null){
			Expression<String> pathObj = queryBuilder.getPathReferenceMap().get(property.getPathReferenceName()).get(property.getFieldName());
			predicate = queryBuilder.getCriteriaBuilder().like(
					queryBuilder.getCriteriaBuilder().upper(pathObj),"%"+value.trim().toUpperCase()+"%"
					);
		}else if(queryBuilder.getReferenceMap().get(property.getReferenceName())!=null){
			Expression<String> pathObj = queryBuilder.getReferenceMap().get(property.getReferenceName()).get(property.getFieldName());

			predicate = queryBuilder.getCriteriaBuilder().like(
					queryBuilder.getCriteriaBuilder().upper(pathObj), "%"+value.trim().toUpperCase()+"%"
					);
		}else{
			Expression<String> pathObj = queryBuilder.getRoot().get(property.getFieldName());

			predicate = queryBuilder.getCriteriaBuilder().like(
					queryBuilder.getCriteriaBuilder().upper(pathObj), "%"+value.trim().toUpperCase()+"%"
					);
		}
		criteria.setPredicate(predicate);
		return criteria;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}
}
