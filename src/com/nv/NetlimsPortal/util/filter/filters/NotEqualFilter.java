package com.nv.NetlimsPortal.util.filter.filters;

/**
 * NotEqualFilter.java
 *
 * Jan 23, 2012
 *
 * @author Asha Chandran 
 */


import javax.persistence.criteria.Predicate;

import com.nv.NetlimsPortal.util.filter.core.Criteria;
import com.nv.NetlimsPortal.util.filter.core.Expression;
import com.nv.NetlimsPortal.util.filter.core.Filter;
import com.nv.NetlimsPortal.util.filter.core.Property;
import com.nv.NetlimsPortal.util.filter.core.PropertyUtil;
import com.nv.NetlimsPortal.util.filter.core.QueryBuilder;





public class NotEqualFilter implements Filter{
	private String name;
	private String value;

	public NotEqualFilter(){
		
	}
	public NotEqualFilter(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public Criteria getPredicate(QueryBuilder queryBuilder,Property property) {
	
		String entityName = property.getEntityName();
		String fieldName = property.getFieldName();
		String referenceName = property.getReferenceName();
		String pathReferenceName = property.getPathReferenceName();
		Class<?> type = PropertyUtil.getFieldType(fieldName, entityName);
		Object propertyValue = PropertyUtil.getPropertyValue(type,value);
		Expression expression = new Expression();
		expression.setName(name);
		expression.setValue(propertyValue);
		Predicate predicate = null;
		if(queryBuilder.getPathReferenceMap().get(pathReferenceName)!=null){
			predicate = queryBuilder.getCriteriaBuilder().
					notEqual(queryBuilder.getPathReferenceMap().get(pathReferenceName).get(fieldName),
							PropertyUtil.getParameterExpForProperty(queryBuilder.getCriteriaBuilder(), name, type));
		}else if(queryBuilder.getReferenceMap().get(referenceName)!=null){
			predicate = queryBuilder.getCriteriaBuilder().
					notEqual(queryBuilder.getReferenceMap().get(referenceName).get(fieldName),
							PropertyUtil.getParameterExpForProperty(queryBuilder.getCriteriaBuilder(), name, type));
		}else{
			predicate = queryBuilder.getCriteriaBuilder().notEqual(queryBuilder.getRoot().get(fieldName),
					PropertyUtil.getParameterExpForProperty(queryBuilder.getCriteriaBuilder(), name, type));
		}

		Criteria criteria = new Criteria();
		criteria.setPredicate(predicate);
		criteria.setExpression(expression);
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
