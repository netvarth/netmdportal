/**
 * AppointmentQueryBuilder.java
 * @author netvarth
 *
 * Version 1.0 Jul 19, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.util.filter.queryBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.dao.FilterDao;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.pl.entity.ResultTbl;
import com.nv.youNeverWait.util.filter.core.Criteria;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.filters.LessThanFilter;
import com.nv.youNeverWait.util.filter.filters.LessThanOrEqualFilter;

/**
 *
 *
 * @author Luciya Jose
 */
public class AppointmentQueryBuilder extends RootQueryBuilder<PatientAppointmentTbl> {
	

	/**
	 * 
	 */
	public AppointmentQueryBuilder() {
	}
	/**
	 * Initialize criteria query, root and criteriaBuilder
	 */
	@Override 
	public void init(FilterDao dao) {
		super.init(dao, PatientAppointmentTbl.class);
		SimpleDateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Filter timeFilter=new LessThanFilter();
		timeFilter.setName("appointmentTime");
		timeFilter.setValue(df.format(new Date()));
		this.addFilter(timeFilter);

	}
	/**
	 * Add filter to query builder then generate predicates,
	 * which are needed to build query for specified filter
	 * @param filter
	 */
	@Override
	public void addFilter(Filter filter) {
		String referenceName = AppointmentPropertyEnum.valueOf(filter.getName()).getReferenceName();
		String pathReferenceName =AppointmentPropertyEnum.valueOf(filter.getName()).getPathReferenceName();

		if(referenceName!=null && !referenceName.equals("")){
			if(referenceMap.get(referenceName)==null){
				referenceMap.put(referenceName, getJoin(referenceName));
			}
		}
		if(pathReferenceName!=null && !pathReferenceName.equals("")){
			if(pathReferenceMap.get(pathReferenceName)==null){
				pathReferenceMap.put(pathReferenceName, referenceMap.get(referenceName).get(pathReferenceName));
			}
		}
		Criteria criteria = filter.getPredicate(this, AppointmentPropertyEnum.valueOf(filter.getName()));
		if(criteria.getPredicate()!=null){
			predicates.add(criteria.getPredicate());
		}
	
		if(criteria.getExpression()!=null){
			expressions.add(criteria.getExpression());
		}
		
	}

}
