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
package com.nv.youNeverWait.user.pl.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.LabTbl;
import com.nv.youNeverWait.pl.entity.OrderTransferTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.OrderDetails;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTransferResponse;
import com.nv.youNeverWait.rs.dto.OrderTypeDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.LabDao;
import com.nv.youNeverWait.user.pl.dao.OrderDao;

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
		List<OrderTransfer> orderList = new ArrayList<OrderTransfer>();
		List<OrderTransferTbl> recievedOrders = getTransferredOrders(
				header.getHeadOfficeId(), header.getBranchId(), syncTime,
				currentSyncTime);
		for (OrderTransferTbl order : recievedOrders) {
			OrderTransfer newOrder = new OrderTransfer();
			newOrder.setSourceLabId(order.getSourceLab().getId());
			newOrder.setSourceLabBranchId(order.getSourceBranch().getId());
			newOrder.setOrderUid(order.getOrderUid());
			newOrder.setOrderDetails(order.getOrderDetails());
			orderList.add(newOrder);
		}
		orderDetail.setOrders(orderList);
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
	public OrderTransferResponse transferOrder(OrderTransfer orderTranfer) {
		OrderTransferResponse response = new OrderTransferResponse();
		LabBranchTbl sourceLabBranch = getLabBranchId(
				orderTranfer.getSourceLabBranchId(),
				orderTranfer.getSourceLabId());
		LabBranchTbl destinationLabBranch = getLabBranchId(
				orderTranfer.getDestinationLabBranchId(),
				orderTranfer.getDestinationLabId());
		if (sourceLabBranch == null) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSourceLabBranch);

			se.setDisplayErrMsg(true);
			throw se;

		}
		if (destinationLabBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDestinationLabBranch);

			se.setDisplayErrMsg(true);
			throw se;
		}
		OrderTransferTbl orderTransferTbl = new OrderTransferTbl();
		orderTransferTbl.setSourceLab(sourceLabBranch.getLabTbl());
		orderTransferTbl.setSourceBranch(sourceLabBranch);
		orderTransferTbl.setDestinationLab(destinationLabBranch.getLabTbl());
		orderTransferTbl.setDestinationBranch(destinationLabBranch);
		orderTransferTbl.setOrderDetails(orderTranfer.getOrderDetails());

		/** Setting unique id if there is no unique id given from **/
		if (orderTranfer.getOrderUid()==null && orderTranfer.getOrderUid().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.OrderUidNull);

			se.setDisplayErrMsg(true);
			throw se;
		} else {
			orderTransferTbl.setOrderUid(orderTranfer.getOrderUid());
		}

		Date createdTime = new Date();
		orderTransferTbl.setCreatedDateTime(createdTime);
		orderTransferTbl.setUpdatedDateTime(createdTime);
		save(orderTransferTbl);

		response.setOrderUid(orderTransferTbl.getOrderUid());
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

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.OrderDao#getOrderType(int)
	 */
	@Override
	@Transactional
	public OrderTypeDTO getOrderType(int labId) {
		LabTbl lab = getById(LabTbl.class, labId);
		if (lab == null) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(labId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		OrderTypeDTO orderType= new OrderTypeDTO();
		orderType.setOrderTypeCodes(lab.getOrderTypeCode());
		return orderType;
	}
	/**
	 * @return
	 */
	private Integer getLastRecordId() {

		javax.persistence.Query query = em
				.createQuery(Query.GET_LAST_UNIQUE_ID);
		Integer lastId = (Integer) query.getSingleResult();
		if (lastId == null)
			lastId = 0;
		return lastId;
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
