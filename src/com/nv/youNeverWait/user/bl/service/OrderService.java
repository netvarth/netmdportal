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
package com.nv.youNeverWait.user.bl.service;

import java.util.Date;

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.OrderDetails;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTransferResponse;
import com.nv.youNeverWait.rs.dto.OrderTypeDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

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
	public OrderTransferResponse transferOrder(OrderTransfer orderTranferDto);

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
