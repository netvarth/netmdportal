/**
 * OrderDaoImpl.java
 * @author netvarth
 *
 * Version 1.0 Sep 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
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

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;


import com.nv.NetlimsPortal.common.pl.impl.GenericDaoHibernateImpl;
import com.nv.NetlimsPortal.exception.ServiceException;
import com.nv.NetlimsPortal.general.Constants;
import com.nv.NetlimsPortal.pl.dao.LabDao;
import com.nv.NetlimsPortal.pl.dao.OrderDao;
import com.nv.NetlimsPortal.pl.entity.ErrorCodeEnum;
import com.nv.NetlimsPortal.pl.entity.LabBranchTbl;
import com.nv.NetlimsPortal.pl.entity.LabTbl;
import com.nv.NetlimsPortal.pl.entity.OrderBranchTbl;
import com.nv.NetlimsPortal.pl.entity.OrderTransferTbl;
import com.nv.NetlimsPortal.pl.query.Query;
import com.nv.NetlimsPortal.rs.dto.HeaderDTO;
import com.nv.NetlimsPortal.rs.dto.OrderDetails;
import com.nv.NetlimsPortal.rs.dto.OrderTransfer;
import com.nv.NetlimsPortal.rs.dto.OrderTypeDTO;
import com.nv.NetlimsPortal.rs.dto.Parameter;
import com.nv.NetlimsPortal.rs.dto.ResponseDTO;

/**
 * 
 * 
 * @author Luciya Jose
 */
public class OrderDaoImpl extends GenericDaoHibernateImpl implements OrderDao {

	@PersistenceContext()
	private EntityManager em;
	private LabDao labDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.OrderDao#retrieveBranchOrders(com.nv.
	 * youNeverWait.rs.dto.LabHeaderDTO, java.lang.String, java.util.Date)
	 */
	@Override
	@Transactional
	public OrderDetails retrieveBranchOrders(HeaderDTO header,
			String lastSyncTime, Date currentSyncTime) {
		OrderDetails orderDetail = new OrderDetails();

		/* Checking header details */
		labDao.CheckHeaderDetails(header);

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
		List<Object> orderList = new ArrayList<Object>();
		List<OrderTransferTbl> recievedOrders = getTransferredOrders(
				header.getHeadOfficeId(), header.getBranchId(), syncTime,
				currentSyncTime);
		for (OrderTransferTbl order : recievedOrders) {
			Object orderDetails = new Object();
			ObjectMapper maper = new ObjectMapper();
			try {
				orderDetails = maper.readValue(order.getOrderBranchTbl().getOrderDetails(),
						Object.class);
			} catch (Exception e) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.OrderTransferException);
				se.setDisplayErrMsg(true);
				throw se;
			}
			order.setSent(true);  
			update(order);
			orderList.add(orderDetails);
		}
		orderDetail.setOrders(orderList);
		orderDetail.setLastOrderSyncTime(sdf.format(currentSyncTime));
		orderDetail.setSuccess(true);
		return orderDetail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.LabDao#transferOrder(com.nv.youNeverWait
	 * .rs.dto.OrderTransfer)
	 */
	@Override
	@Transactional
	public ResponseDTO transferOrder(OrderTransfer orderTranfer) {
		ResponseDTO response = new ResponseDTO();
		LabBranchTbl sourceLabBranch = getLabBranchId(
				orderTranfer.getSourceLabBranchId(),
				orderTranfer.getSourceLabId());

		if (sourceLabBranch == null) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSourceLabBranch);

			se.setDisplayErrMsg(true);
			throw se;

		}

		/* Saving orders in order branch tbl */
		OrderBranchTbl orderBranchTbl = new OrderBranchTbl();
		orderBranchTbl.setLabTbl(sourceLabBranch.getLabTbl());
		orderBranchTbl.setLabBranchTbl(sourceLabBranch);
		orderBranchTbl.setOrderDetails(orderTranfer.getOrderDetails());

		/** Setting unique id if there is no unique id given from **/
		if (orderTranfer.getOrderUid() == null || orderTranfer.getOrderUid().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.OrderUidNull);

			se.setDisplayErrMsg(true);
			throw se;
		} else {
			orderBranchTbl.setOrderUid(orderTranfer.getOrderUid());
		}

		Date createdTime = new Date();
		orderBranchTbl.setCreatedDateTime(createdTime);
		orderBranchTbl.setUpdatedDateTime(createdTime);
		save(orderBranchTbl);

		/* Saving details in order transfer tbl */
		for (Integer destinationBranch : orderTranfer.getDestinationBranches()) {
			/* Checking whether there is any labs and branches */
			LabBranchTbl destinationLabBranch = getLabBranchId(
					destinationBranch, orderTranfer.getDestinationLabId());

			if (destinationLabBranch == null) {
				 ServiceException se = new ServiceException(
				 ErrorCodeEnum.InvalidDestinationLabBranch);
				
				 se.setDisplayErrMsg(true);
				 throw se;

			}
			OrderTransferTbl orderTransferTbl = new OrderTransferTbl();
			orderTransferTbl.setOrderBranchTbl(orderBranchTbl);
			orderTransferTbl.setLabBranchTbl(destinationLabBranch);
			orderTransferTbl.setLabTbl(destinationLabBranch.getLabTbl());
			orderTransferTbl.setCreatedDateTime(createdTime);
			orderTransferTbl.setUpdatedDateTime(createdTime);
			save(orderTransferTbl);

		}
		response.setGlobalId(orderBranchTbl.getId());
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.OrderDao#setOrderType(com.nv.youNeverWait
	 * .rs.dto.OrderTypeDTO)
	 */
	@Override
	@Transactional
	public ResponseDTO setOrderType(OrderTypeDTO orderTypeDetails) {
		ResponseDTO response = new ResponseDTO();
		LabTbl lab = getById(LabTbl.class, orderTypeDetails.getLabId());
		if (lab == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(orderTypeDetails.getLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		lab.setOrderTypeCode(orderTypeDetails.getOrderTypeCodes());
		lab.setUpdateDateTime(new Date());
		update(lab);
		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.OrderDao#getOrderType(int)
	 */
	@Override
	@Transactional
	public OrderTypeDTO getOrderType(int labId) {
		LabTbl lab = getById(LabTbl.class, labId);
		if (lab == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		OrderTypeDTO orderType = new OrderTypeDTO();
		orderType.setOrderTypeCodes(lab.getOrderTypeCode());
		return orderType;
	}

	/**
	 * 
	 * @param branchId
	 * @param labId
	 * @return LabBranchTbl
	 */
	public LabBranchTbl getByLabId(int branchId, int labId) {
		javax.persistence.Query query = em.createQuery(Query.GET_BRANCH);
		query.setParameter("param1", branchId);
		query.setParameter("param2", labId);
		return executeUniqueQuery(LabBranchTbl.class, query);
	}

	/**
	 * @param i
	 * @param sourceLabBranchId
	 * @return
	 */
	private LabBranchTbl getLabBranchId(int labBranchId, int labId) {

		javax.persistence.Query query = em.createQuery(Query.GET_LAB_BRANCH);
		query.setParameter("param1", labBranchId);
		query.setParameter("param2", labId);
		return executeUniqueQuery(LabBranchTbl.class, query);
	}

	/**
	 * @param labId
	 * @param labBranchId
	 * @param syncTime
	 * @param currentSyncTime
	 * @return
	 */
	private List<OrderTransferTbl> getTransferredOrders(int labId,
			int labBranchId, Date syncTime, Date currentSyncTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_ORDERS);
		query.setParameter("param1", labId);
		query.setParameter("param2", labBranchId);
		query.setParameter("param3", syncTime);
		query.setParameter("param4", currentSyncTime);
		return executeQuery(OrderTransferTbl.class, query);
	}

	/**
	 * @param labDao
	 *            the labDao to set
	 */
	public void setLabDao(LabDao labDao) {
		this.labDao = labDao;
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
