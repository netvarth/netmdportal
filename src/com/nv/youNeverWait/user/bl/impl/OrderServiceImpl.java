/**
 * OrderServiceImpl.java
 * @author netvarth
 *
 * Version 1.0 Sep 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.impl;

import java.util.Date;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.LabHeaderDTO;
import com.nv.youNeverWait.rs.dto.OrderDetails;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTransferResponse;
import com.nv.youNeverWait.rs.dto.OrderTypeDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.user.bl.service.OrderManager;
import com.nv.youNeverWait.user.bl.validation.OrderValidator;
import com.nv.youNeverWait.user.pl.dao.OrderDao;

/**
 * 
 * 
 * @author Luciya Jose
 */
public class OrderServiceImpl implements OrderManager {
	private OrderDao orderDao;
	private OrderValidator validator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.bl.service.OrderManager#retrieveBranchOrders
	 * (com.nv.youNeverWait.rs.dto.LabHeaderDTO, java.lang.String,
	 * java.util.Date)
	 */
	@Override
	public OrderDetails retrieveBranchOrders(LabHeaderDTO header,
			String lastSyncTime, Date currentSyncTime) {
		OrderDetails orderDetails = orderDao.retrieveBranchOrders(header,
				lastSyncTime, currentSyncTime);
		return orderDetails;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.bl.service.LabService#transferOrder(com.nv.
	 * youNeverWait.rs.dto.OrderTransfer)
	 */
	@Override
	public OrderTransferResponse transferOrder(OrderTransfer orderTranfer) {

		validator.validateOrderTransferDetails(orderTranfer);
		OrderTransferResponse response = orderDao.transferOrder(orderTranfer);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.bl.service.OrderManager#setOrderType(com.nv.
	 * youNeverWait.rs.dto.OrderTypeDTO)
	 */
	@Override
	public ResponseDTO setOrderType(OrderTypeDTO orderTypeDetails) {
		validator.validateOrderTypeDetails(orderTypeDetails);
		ResponseDTO response = orderDao.setOrderType(orderTypeDetails);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.bl.service.OrderManager#getOrderType(int)
	 */
	@Override
	public OrderTypeDTO getOrderType(int labId) {
		if(labId<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidLabId);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		OrderTypeDTO response = orderDao.getOrderType(labId);
		return response;
	}

	/**
	 * @return the validator
	 */
	public OrderValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public void setValidator(OrderValidator validator) {
		this.validator = validator;
	}

	/**
	 * @return the orderDao
	 */
	public OrderDao getOrderDao() {
		return orderDao;
	}

	/**
	 * @param orderDao
	 *            the orderDao to set
	 */
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

}
