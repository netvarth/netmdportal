package com.nv.youNeverWait.util.filter.queryBuilder;

import com.nv.youNeverWait.pl.dao.FilterDao;
import com.nv.youNeverWait.pl.entity.SyncLogTbl;
import com.nv.youNeverWait.util.filter.core.Criteria;
import com.nv.youNeverWait.util.filter.core.Filter;



public class SyncLogQueryBuilder extends RootQueryBuilder<SyncLogTbl> {

	public SyncLogQueryBuilder(){
	}

	
	@Override
	public void addFilter(Filter filter) {
		String referenceName = SyncLogPropertyEnum.valueOf(filter.getName()).getReferenceName();
		String pathReferenceName =SyncLogPropertyEnum.valueOf(filter.getName()).getPathReferenceName();

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
		Criteria criteria = filter.getPredicate(this, SyncLogPropertyEnum.valueOf(filter.getName()));
		if(criteria.getPredicate()!=null){
			predicates.add(criteria.getPredicate());
		}
	
		if(criteria.getExpression()!=null){
			expressions.add(criteria.getExpression());
		}
		
		
	}

	@Override
	public void init(FilterDao filterDao) {
		super.init(filterDao, SyncLogTbl.class);
	}

	

}
