/**
 * GenericDaoHibernateImpl.java
 */
package com.nv.youNeverWait.pl.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;
import com.nv.framework.sendmsg.PendingMsgPojoTbl;
import com.nv.framework.sendmsg.common.SendMsgBaseDAO;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.dao.GenericDao;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.PendingMessageTbl;

public class GenericDaoHibernateImpl implements GenericDao, SendMsgBaseDAO {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(GenericDaoHibernateImpl.class);

	@PersistenceContext()
	private EntityManager em;

	public GenericDaoHibernateImpl() {

	}

	@Transactional(readOnly = false)
	public void update(final Object obj) {
		try {
			em.merge(obj);
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	@Transactional(readOnly = false)
	public void delete(Object obj) {
		try {
			em.remove(obj);
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	@Transactional(readOnly = false)
	public void save(Object obj) {
		try {
			em.persist(obj);
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	@Transactional(readOnly = false)
	public <T> T getById(Class<T> a, int id) {
		try {
			T obj = em.find(a, id);
			return obj;
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	@Transactional(readOnly = false)
	public <T> List<T> loadAll(Class<T> className) {
		List<T> response = null;
		try {
			Query query = em.createQuery("from " + className.getName());
			response = query.getResultList();
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return response;
	}

	@Transactional(readOnly = false)
	public <T> Object getObject(String a) {

		javax.persistence.Query query = em
				.createQuery("from OrderTbl order where order.uid = :param1");
		query.setParameter("param1", a);
		return query.getSingleResult();
	}

	@Transactional(readOnly = false)
	public <T> List<T> getSpecimenList(int id) {
		javax.persistence.Query query = em
				.createQuery("from OrderTbl as order where order.id=:" + id
						+ ";");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public <T> T getSpecimenByName(Class<T> className, String name) {
		Query query = em.createQuery("from " + className.getName()
				+ " as obj where obj.name=:name");
		query.setParameter("name", name);
		return (T) query.getSingleResult();
	}

	public <T> T getTestSpecimenById(Class<T> className, int testUid,
			int specimenUid) {
		Query query = em
				.createQuery("from "
						+ className.getName()
						+ " as obj where obj.testTbl.uid=:testUid and obj.specimenTbl.uid=:specimenUid");
		query.setParameter("testUid", testUid);
		query.setParameter("specimenUid", specimenUid);
		return (T) query.getSingleResult();
	}

	@Transactional(readOnly = false)
	public <T> T getByUID(Class<T> className, int uid) {
		try {
			Query query = em.createQuery("from " + className.getName()
					+ " as obj where obj.uid=:uid");
			query.setParameter("uid", uid);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	@Transactional(readOnly = false)
	public <T> T getByUid(Class<T> className, int uid) {
		try {
			Query query = em.createQuery("from " + className.getName()
					+ " as obj where obj.uid=:uid");
			query.setParameter("uid", uid);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (ClassCastException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.weblims.pl.dao.GenericDao#getSequence(java.lang.String)
	 */
	@Override
	public BigInteger getNextSequence(String sequence) {
		try {
			Query query = em.createNativeQuery("select nextval('" + sequence
					+ "')");
			return (BigInteger) query.getSingleResult();
		} catch (RuntimeException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	@Transactional(readOnly = false)
	public <T> List<T> executeQuery(Class<T> className, Query query) {
		List<T> response = null;
		try {
			response = query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (ClassCastException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return response;
	}

	@Transactional(readOnly = false)
	public <T> T executeUniqueQuery(Class<T> className, Query query) {
		T response = null;
		try {
			response = (T) query.getSingleResult();
		} catch (NoResultException e) {
			//e.printStackTrace();
			return null;
		} catch (ClassCastException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return response;
	}

	@Override
	public PendingMsgPojoTbl getPendingMsgTblById(int id) {
		PendingMessageTbl pTbl = (PendingMessageTbl) getById(
				PendingMessageTbl.class, id);
		return pendingMsgToPojo(pTbl);
	}

	@Transactional
	@Override
	public void saveMsg(PendingMsgPojoTbl obj) {

		PendingMessageTbl ptbl = pojoToPendingMsg(obj);
		save(ptbl);
		obj.setId(ptbl.getId());
	}

	@Transactional
	@Override
	public void deleteMsg(PendingMsgPojoTbl obj) {
		PendingMessageTbl pTbl = (PendingMessageTbl) getById(
				PendingMessageTbl.class, obj.getId());
		delete(pTbl);
	}

	@Transactional
	@Override
	public void updateMsg(PendingMsgPojoTbl obj) {
		update(pojoToPendingMsg(obj));
	}

	private PendingMsgPojoTbl pendingMsgToPojo(PendingMessageTbl pTbl) {
		PendingMsgPojoTbl pPojoTbl = new PendingMsgPojoTbl();
		pPojoTbl.setApplicationSpecifier(pTbl.getApplicationSpecifier());
		pPojoTbl.setAttemptsMade(pTbl.getAttemptsMade());
		pPojoTbl.setClusterId(pTbl.getClusterId());
		pPojoTbl.setCommunicationType(pTbl.getCommunicationType());
		pPojoTbl.setContent(pTbl.getContent());
		pPojoTbl.setErrorCode(pTbl.getErrorCode());
		pPojoTbl.setId(pTbl.getId());
		pPojoTbl.setLastAttemptOn(pTbl.getLastAttemptOn());
		pPojoTbl.setStatus(pTbl.getStatus());

		return pPojoTbl;
	}

	private PendingMessageTbl pojoToPendingMsg(PendingMsgPojoTbl pPojoTbl) {
		PendingMessageTbl pTbl = new PendingMessageTbl();
		pTbl.setApplicationSpecifier(pPojoTbl.getApplicationSpecifier());
		pTbl.setAttemptsMade(pPojoTbl.getAttemptsMade());
		pTbl.setClusterId(pPojoTbl.getClusterId());
		pTbl.setCommunicationType(pPojoTbl.getCommunicationType());
		pTbl.setContent(pPojoTbl.getContent());
		pTbl.setErrorCode(pPojoTbl.getErrorCode());
		pTbl.setLastAttemptOn(pPojoTbl.getLastAttemptOn());
		pTbl.setStatus(pPojoTbl.getStatus());
		return pTbl;
	}

	@Override
	public List<Integer> getInqueueMsg(String type, String context) {
		Query query = em
				.createQuery(com.nv.youNeverWait.security.pl.Query.GET_INQUEUE_FROM_TABLE);
		query.setParameter("param1", type);
		query.setParameter("param2", "youNeverWait");
		query.setParameter("param3", "INQUEUE");
		List<PendingMessageTbl> pendingMessages = executeQuery(
				PendingMessageTbl.class, query);
		List<Integer> inqueMsgIds = new ArrayList<Integer>();
		for (PendingMessageTbl pendingMessageTbl : pendingMessages) {
			inqueMsgIds.add(pendingMessageTbl.getId());

		}
		return inqueMsgIds;
	}

	@Override
	public List<Integer> getNewMsg(String type, String count, String context) {
		Query query = em
				.createQuery(com.nv.youNeverWait.security.pl.Query.GET_NEW_MSG);
		query.setParameter("param1", type);
		query.setParameter("param2", "youNeverWait");
		query.setParameter("param3", "NEW");
		query.setFirstResult(0);
		query.setMaxResults(Integer.parseInt(count));
		List<PendingMessageTbl> pendingMessages = executeQuery(
				PendingMessageTbl.class, query);
		List<Integer> inqueMsgIds = new ArrayList<Integer>();
		for (PendingMessageTbl pendingMessageTbl : pendingMessages) {
			inqueMsgIds.add(pendingMessageTbl.getId());
		}
		return inqueMsgIds;
	}

	@Override
	public Long getNewMsgCount(String type, String context) {
		Query query = em
				.createQuery(com.nv.youNeverWait.security.pl.Query.GET_COUNT_NEW_FROM_TABLE);
		query.setParameter("param1", type);
		query.setParameter("param2", "youNeverWait");
		query.setParameter("param3", "NEW");
		List<PendingMessageTbl> pendingMessages = executeQuery(
				PendingMessageTbl.class, query);
		if (pendingMessages == null) {
			return (long) 0;
		}
		return (long) pendingMessages.size();
	}

	@Override
	public void setCompanyType(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

}