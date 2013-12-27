/**
 * TestQueryBuilder.java
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
import com.nv.youNeverWait.pl.entity.TestTbl;
import com.nv.youNeverWait.util.filter.core.Criteria;
import com.nv.youNeverWait.util.filter.core.Filter;

/**
 *
 *
 * @author Luciya Jose
 */
public class TestQueryBuilder extends RootQueryBuilder<TestTbl> {

	public TestQueryBuilder() {
	}
	
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.util.filter.core.QueryBuilder#init(com.nv.youNeverWait.pl.dao.FilterDao)
	 */
	@Override
	public void init(FilterDao filterDao) {
		super.init(filterDao, TestTbl.class);
		
	}
	
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.util.filter.core.QueryBuilder#addFilter(com.nv.youNeverWait.util.filter.core.Filter)
	 */
	@Override
	public void addFilter(Filter filter) {
		String referenceName = TestPropertyEnum.valueOf(filter.getName())
				.getReferenceName();
		String pathReferenceName = TestPropertyEnum.valueOf(filter.getName())
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
				TestPropertyEnum.valueOf(filter.getName()));
		if (criteria.getPredicate() != null) {
			predicates.add(criteria.getPredicate());
		}

		if (criteria.getExpression() != null) {
			expressions.add(criteria.getExpression());
		}

	}
		

	

	
}
