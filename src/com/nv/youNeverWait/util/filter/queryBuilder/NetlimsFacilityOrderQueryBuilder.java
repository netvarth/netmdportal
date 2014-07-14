/**
 * 
 */
package com.nv.youNeverWait.util.filter.queryBuilder;

import com.nv.youNeverWait.pl.dao.FilterDao;
import com.nv.youNeverWait.pl.entity.NetlimsOrderTbl;
import com.nv.youNeverWait.util.filter.core.Criteria;
import com.nv.youNeverWait.util.filter.core.Filter;

/**
 * @author Mani E.V
 *
 */
public class NetlimsFacilityOrderQueryBuilder extends RootQueryBuilder<NetlimsOrderTbl> {

	@Override
	public void addFilter(Filter filter) {
		String referenceName = NetlimsFacilityOrderPropertyEnum.valueOf(filter.getName()).getReferenceName();
		String pathReferenceName =NetlimsFacilityOrderPropertyEnum.valueOf(filter.getName()).getPathReferenceName();

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
		Criteria criteria = filter.getPredicate(this, NetlimsFacilityOrderPropertyEnum.valueOf(filter.getName()));
		if(criteria.getPredicate()!=null){
			predicates.add(criteria.getPredicate());
		}
	
		if(criteria.getExpression()!=null){
			expressions.add(criteria.getExpression());
		}
	}

	@Override
	public void init(FilterDao filterDao) {
		super.init(filterDao, NetlimsOrderTbl.class);
	}

}
