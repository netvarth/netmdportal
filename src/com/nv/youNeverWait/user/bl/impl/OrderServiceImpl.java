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

import com.nv.youNeverWait.rs.dto.LabHeaderDTO;
import com.nv.youNeverWait.rs.dto.OrderDetails;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTransferResponse;
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

	
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.OrderManager#retrieveBranchOrders(com.nv.youNeverWait.rs.dto.LabHeaderDTO, java.lang.String, java.util.Date)
	 */
	@Override
	public OrderDetails retrieveBranchOrders(LabHeaderDTO header,
			String lastSyncTime, Date currentSyncTime) {
	OrderDetails orderDetails=orderDao.retrieveBranchOrders(header,lastSyncTime,currentSyncTime);
	return orderDetails;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.LabService#transferOrder(com.nv.youNeverWait.rs.dto.OrderTransfer)
	 */
	@Override
	public OrderTransferResponse transferOrder(OrderTransfer orderTranfer) {
	
		validator.validateOrderTransferDetails(orderTranfer);
		OrderTransferResponse response= orderDao.transferOrder(orderTranfer);
		return response;
	}
	

	/**
	 * @return the validator
	 */
	public OrderValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator the validator to set
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
	 * @param orderDao the orderDao to set
	 */
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

}
