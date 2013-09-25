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

import com.nv.youNeverWait.rs.dto.LabHeaderDTO;
import com.nv.youNeverWait.rs.dto.OrderDetails;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTransferResponse;

/**
 *
 *
 * @author Luciya Jose
 */
public interface OrderManager {
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
	OrderDetails retrieveBranchOrders(LabHeaderDTO header, String lastSyncTime,
			Date currentSyncTime);

}
