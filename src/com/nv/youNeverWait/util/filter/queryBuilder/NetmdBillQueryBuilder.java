/**
 * NetmdBillQueryBuilder.java
 * @author netvarth
 *
 * Version 1.0 Dec 13, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.util.filter.queryBuilder;


import com.nv.youNeverWait.pl.dao.FilterDao;
import com.nv.youNeverWait.pl.entity.NetmdBillTbl;
import com.nv.youNeverWait.util.filter.core.Criteria;
import com.nv.youNeverWait.util.filter.core.Filter;

/**
 *
 *
 * @author Luciya Jose
 */
public class NetmdBillQueryBuilder extends RootQueryBuilder<NetmdBillTbl>{

	public NetmdBillQueryBuilder(){
	}

	/**
	 * Initialize criteria query, root and criteriaBuilder
	 */
	@Override 
	public void init(FilterDao dao) {
		super.init(dao, NetmdBillTbl.class);
	}

	/**
	 * Add filter to query builder then generate predicates,
	 * which are needed to build query for specified filter
	 * @param filter
	 */
	@Override
	public void addFilter(Filter filter) {
		String referenceName = BillPropertyEnum.valueOf(filter.getName()).getReferenceName();
		String pathReferenceName =BillPropertyEnum.valueOf(filter.getName()).getPathReferenceName();

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
		Criteria criteria = filter.getPredicate(this, BillPropertyEnum.valueOf(filter.getName()));
		if(criteria.getPredicate()!=null){
			predicates.add(criteria.getPredicate());
		}
	
		if(criteria.getExpression()!=null){
			expressions.add(criteria.getExpression());
		}
		
	}


}
