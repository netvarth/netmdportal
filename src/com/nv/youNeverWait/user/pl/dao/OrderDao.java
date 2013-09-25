/**
 * OrderDao.java
 * @author netvarth
 *
 * Version 1.0 Sep 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.pl.dao;

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
public interface OrderDao {

	/**
	 * @param orderTranfer
	 * @return
	 */
	public OrderTransferResponse transferOrder(OrderTransfer orderTranfer);
	
	/**
	 * @param header
	 * @param lastSyncTime
	 * @param currentSyncTime
	 * @return
	 */
	OrderDetails retrieveBranchOrders(LabHeaderDTO header, String lastSyncTime,
			Date currentSyncTime);

}
