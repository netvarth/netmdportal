/**
 * NetPosQueryBuilder.java
 * Jithinraj
 * Dec 27, 2013
 */
package com.nv.youNeverWait.util.filter.queryBuilder;

import com.nv.youNeverWait.pl.dao.FilterDao;
import com.nv.youNeverWait.pl.entity.NetmdTbl;
import com.nv.youNeverWait.pl.entity.NetposTbl;
import com.nv.youNeverWait.util.filter.core.Criteria;
import com.nv.youNeverWait.util.filter.core.Filter;

/**
 * @author Mani
 *
 */
public class NetPosQueryBuilder extends RootQueryBuilder<NetposTbl>{

	public NetPosQueryBuilder(){
	}

	/**
	 * Initialize criteria query, root and criteriaBuilder
	 */
	@Override 
	public void init(FilterDao dao) {
		super.init(dao, NetposTbl.class);
	}

	/**
	 * Add filter to query builder then generate predicates,
	 * which are needed to build query for specified filter
	 * @param filter
	 */
	@Override
	public void addFilter(Filter filter) {
		String referenceName = NetPosPropertyEnum.valueOf(filter.getName()).getReferenceName();
		String pathReferenceName =NetPosPropertyEnum.valueOf(filter.getName()).getPathReferenceName();

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
		Criteria criteria = filter.getPredicate(this, NetPosPropertyEnum.valueOf(filter.getName()));
		if(criteria.getPredicate()!=null){
			predicates.add(criteria.getPredicate());
		}
	
		if(criteria.getExpression()!=null){
			expressions.add(criteria.getExpression());
		}
		
	}
	}


