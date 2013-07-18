/**
 * LessThanFilter.java
 *
 * Jan 23,2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.util.filter.filters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.util.filter.core.Criteria;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.core.PropertyUtil;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;


public class LessThanFilter implements Filter{
	private String name;
	private String value;

	public LessThanFilter(){

	}
	public LessThanFilter(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * Get predicate
	 */
	@Override
	public Criteria  getPredicate(QueryBuilder queryBuilder,Property property) {

		Class<?> type = PropertyUtil.getFieldType(property.getFieldName(), property.getEntityName());
		Predicate predicate = null;
		if(queryBuilder.getPathReferenceMap().get(property.getPathReferenceName())!=null){
			predicate = createPredicate(queryBuilder,
					queryBuilder.getPathReferenceMap().get(property.getPathReferenceName()).get(property.getFieldName()),
					type, value);

		}else if(queryBuilder.getReferenceMap().get(property.getReferenceName())!=null){
			predicate = createPredicate(queryBuilder,
					queryBuilder.getReferenceMap().get(property.getReferenceName()).get(property.getFieldName()),
					type, value);
		}else{
			predicate = createPredicate(queryBuilder,queryBuilder.getRoot().get(property.getFieldName()), type, value);
		}

		Criteria criteria = new Criteria();
		criteria.setPredicate(predicate);
		return criteria;
	}
	
	/**
	 * Create predicate according to  type of property value
	 * @param queryBuilder
	 * @param path
	 * @param type
	 * @param value
	 * @return Predicate
	 *
	 */
	public Predicate createPredicate(QueryBuilder queryBuilder,Path<?> path,Class<?> type, String value){
		Predicate predicate = null;
		if(type.getSimpleName().equals("Integer")){
			Path<Integer> pathInt = (Path<Integer>) path;
			predicate = queryBuilder.getCriteriaBuilder().lessThan(pathInt,Integer.parseInt(value));
			return predicate;
		}
		if(type.getSimpleName().equals("float")){
			Path<Float> pathFloat = (Path<Float>) path;
			predicate = queryBuilder.getCriteriaBuilder().lessThan(pathFloat,Float.parseFloat(value));
			return predicate;
		}
		if(type.getSimpleName().equals("Date")){
			Path<Date> pathDate = (Path<Date>) path;
			SimpleDateFormat df =new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
			Date date =null;
			try {
				date = df.parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			predicate = queryBuilder.getCriteriaBuilder().lessThan(pathDate,date);
			return predicate;
		}
		if(type.getSimpleName().equals("double")){
			Path<Double> pathDouble = (Path<Double>) path;
			predicate = queryBuilder.getCriteriaBuilder().lessThan(pathDouble,Double.parseDouble(value));
			return predicate;
		}
		return predicate;

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
