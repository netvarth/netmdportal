/**
 * SpecimenQueryBuilder.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.util.filter.queryBuilder;


import com.nv.youNeverWait.pl.dao.FilterDao;
import com.nv.youNeverWait.pl.entity.SpecimenTbl;
import com.nv.youNeverWait.util.filter.core.Criteria;
import com.nv.youNeverWait.util.filter.core.Filter;

/**
 *
 *
 * @author Luciya Jose
 */
public class SpecimenQueryBuilder extends RootQueryBuilder<SpecimenTbl>{

	public SpecimenQueryBuilder(){
		
	}
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.util.filter.core.QueryBuilder#addFilter(com.nv.youNeverWait.util.filter.core.Filter)
	 */
	@Override
	public void addFilter(Filter filter) {
		String referenceName = SpecimenPropertyEnum.valueOf(filter.getName())
				.getReferenceName();
		String pathReferenceName = SpecimenPropertyEnum.valueOf(filter.getName())
				.getPathReferenceName();

		if (referenceName != null && !referenceName.equals("")) {
			if (referenceMap.get(referenceName) == null) {
				referenceMap.put(referenceName, getJoin(referenceName));
			}
		}
		if (pathReferenceName != null && !pathReferenceName.equals("")) {
			if (pathReferenceMap.get(pathReferenceName) == null) {
				pathReferenceMap.put(pathReferenceName,
						referenceMap.get(referenceName).get(pathReferenceName));
			}
		}
		Criteria criteria = filter.getPredicate(this,
				SpecimenPropertyEnum.valueOf(filter.getName()));
		if (criteria.getPredicate() != null) {
			predicates.add(criteria.getPredicate());
		}

		if (criteria.getExpression() != null) {
			expressions.add(criteria.getExpression());
		}
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.util.filter.core.QueryBuilder#init(com.nv.youNeverWait.pl.dao.FilterDao)
	 */
	@Override
	public void init(FilterDao filterDao) {
		super.init(filterDao, SpecimenTbl.class);
		
	}


}
