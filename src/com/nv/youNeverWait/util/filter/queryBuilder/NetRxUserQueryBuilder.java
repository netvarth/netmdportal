package com.nv.youNeverWait.util.filter.queryBuilder;

import com.nv.youNeverWait.pl.entity.NetrxUserTbl;
import com.nv.youNeverWait.pl.dao.FilterDao;
import com.nv.youNeverWait.util.filter.core.Criteria;
import com.nv.youNeverWait.util.filter.core.Filter;

public class NetRxUserQueryBuilder extends RootQueryBuilder<NetrxUserTbl>{


	public NetRxUserQueryBuilder(){
	}
	/**
	 * Initialize criteria query, root and criteriaBuilder
	 */
	@Override 
	public void init(FilterDao dao) {
		super.init(dao, NetrxUserTbl.class);
	}

	/**
	 * Add filter to query builder then generate predicates,
	 * which are needed to build query for specified filter
	 * @param filter
	 */
	@Override
	public void addFilter(Filter filter) {
		String referenceName = NetRxUserPropertyEnum.valueOf(filter.getName()).getReferenceName();
		String pathReferenceName =NetRxUserPropertyEnum.valueOf(filter.getName()).getPathReferenceName();

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
		Criteria criteria = filter.getPredicate(this, NetRxUserPropertyEnum.valueOf(filter.getName()));
		if(criteria.getPredicate()!=null){
			predicates.add(criteria.getPredicate());
		}
	
		if(criteria.getExpression()!=null){
			expressions.add(criteria.getExpression());
		}
		
	}


}
