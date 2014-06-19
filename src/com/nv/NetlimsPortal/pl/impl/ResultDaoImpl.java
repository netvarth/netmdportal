/**
 * ResultDaoImpl.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.NetlimsPortal.pl.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.nv.NetlimsPortal.common.pl.impl.GenericDaoHibernateImpl;
import com.nv.NetlimsPortal.exception.ServiceException;
import com.nv.NetlimsPortal.general.Constants;
import com.nv.NetlimsPortal.pl.dao.ResultDao;
import com.nv.NetlimsPortal.pl.entity.ErrorCodeEnum;
import com.nv.NetlimsPortal.pl.entity.OrderResultTbl;
import com.nv.NetlimsPortal.pl.query.Query;
import com.nv.NetlimsPortal.rs.dto.HeaderDTO;
import com.nv.NetlimsPortal.rs.dto.OrderTestResult;
import com.nv.NetlimsPortal.rs.dto.OrderTestResultList;


/**
 * 
 */
public class ResultDaoImpl extends GenericDaoHibernateImpl implements ResultDao {

	@PersistenceContext()
	private EntityManager em;

	

	@Override
	@Transactional
	public OrderTestResultList retrieveResults(HeaderDTO header,
			String lastSyncTime, Date currentSyncTime) {
		OrderTestResultList response = new OrderTestResultList();
		List<OrderTestResult> orderTestResultList = new ArrayList<OrderTestResult>();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date syncTime = null;
		try {
			syncTime = sdf.parse(lastSyncTime);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}

		List<OrderResultTbl> orderResultTblList = getResults(
				header.getBranchId(), syncTime, currentSyncTime);
		
		String uid = "";
		String uidC = "";
		OrderTestResult	orderResult=null;
		for (OrderResultTbl orderTestResult : orderResultTblList) {
			uidC = orderTestResult.getOrderBranchTbl().getOrderUid();
			if (uid != uidC) {
				
				if (orderResult !=null){
					System.out.println(orderResult.getTestResultList().size());
					orderTestResultList.add(orderResult);
				}
				orderResult = new OrderTestResult();
				orderResult.setOrderUid(orderTestResult.getOrderBranchTbl().getOrderUid());
				orderResult.addToMap(new String(orderTestResult.getTestUid()), new String(orderTestResult.getResult()));
							

			} else {
			
				orderResult.addToMap(new String(orderTestResult.getTestUid()), new String(orderTestResult.getResult()));
			}
			uid = uidC;
		orderTestResult.setSent(true);  
		update(orderTestResult);
		}
		if (orderResult !=null){
			System.out.println(orderResult.getTestResultList().size());
			orderTestResultList.add(orderResult);
		
		}
		
		
		response.setOrderTestResultList(orderTestResultList);
		response.setSuccess(true);
		return response;
	}

	private List<OrderResultTbl> getResults(int branchId, Date syncTime,
			Date currentSyncTime) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORDER_TEST_RESULTS);
		query.setParameter("param1", branchId);
		query.setParameter("param2", syncTime);
		query.setParameter("param3", currentSyncTime);
		return executeQuery(OrderResultTbl.class, query);
	}

	


	


	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
