/**
 * OrderManager.java
 * @author netvarth
 *
 * Version 1.0 Sep 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.NetlimsPortal.bl.service;

import java.util.Date;

import com.nv.NetlimsPortal.rs.dto.HeaderDTO;
import com.nv.NetlimsPortal.rs.dto.OrderDetails;
import com.nv.NetlimsPortal.rs.dto.OrderTransfer;
import com.nv.NetlimsPortal.rs.dto.OrderTypeDTO;
import com.nv.NetlimsPortal.rs.dto.ResponseDTO;



/**
 *
 *
 * @author Luciya Jose
 */
public interface OrderService {
	/**
	 * @param orderTranferDto
	 * @return
	 */
	public ResponseDTO transferOrder(OrderTransfer orderTranferDto);

	/**
	 * @param header
	 * @param lastSyncTime
	 * @param currentSyncTime
	 * @return
	 */
	OrderDetails retrieveBranchOrders(HeaderDTO header, String lastSyncTime,
			Date currentSyncTime);

	/**
	 * @param orderTypeDetails
	 * @return
	 */
	public ResponseDTO setOrderType(OrderTypeDTO orderTypeDetails);

	/**
	 * @param labId
	 * @return
	 */
	public OrderTypeDTO getOrderType(int labId);

}
