/**
 * AppointmentQueryBuilder.java
 * @author Ricky
 *
 * Version 1.0 Jul 19, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.util.filter.queryBuilder;

import com.nv.youNeverWait.pl.dao.FilterDao;
import com.nv.youNeverWait.pl.entity.LabFacilityTbl;
import com.nv.youNeverWait.util.filter.core.Criteria;
import com.nv.youNeverWait.util.filter.core.Filter;

/**
 *
 *
 * @author Ricky John
 */
public class FacilityQueryBuilder extends RootQueryBuilder<LabFacilityTbl> {
	

	/**
	 * 
	 */
	public FacilityQueryBuilder() {
	}
	@Override
	public void addFilter(Filter filter) {
		String referenceName = FacilityPropertyEnum.valueOf(filter.getName()).getReferenceName();
		String pathReferenceName =FacilityPropertyEnum.valueOf(filter.getName()).getPathReferenceName();

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
		Criteria criteria = filter.getPredicate(this, FacilityPropertyEnum.valueOf(filter.getName()));
		if(criteria.getPredicate()!=null){
			predicates.add(criteria.getPredicate());
		}
	
		if(criteria.getExpression()!=null){
			expressions.add(criteria.getExpression());
		}
	}

	@Override
	public void init(FilterDao filterDao) {
		super.init(filterDao, LabFacilityTbl.class);
	}
}
