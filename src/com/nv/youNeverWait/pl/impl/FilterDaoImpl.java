/**
 * FilterDaoImpl.java
 *
 * Jan 24,2013
 * 
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.pl.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.nv.youNeverWait.pl.dao.FilterDao;

public class FilterDaoImpl extends GenericDaoHibernateImpl implements FilterDao{
	@PersistenceContext()
	private EntityManager em;
	
	@Override
	public EntityManager getEM() {
		return em;
	}

}
