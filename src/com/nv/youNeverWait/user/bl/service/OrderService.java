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
import java.util.List;

import com.nv.security.youNeverWait.User;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.ListResponse;
import com.nv.youNeverWait.rs.dto.Order;
import com.nv.youNeverWait.rs.dto.OrderCountFilterDto;
import com.nv.youNeverWait.rs.dto.OrderDetails;
import com.nv.youNeverWait.rs.dto.OrderTestResultDTO;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
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
	 * @return ResponseDTO
	 */
	public ResponseDTO transferOrder(OrderTransfer orderTranferDto);

	/**
	 * @param header
	 * @param lastSyncTime
	 * @param currentSyncTime
	 * @return OrderDetails
	 */
	OrderDetails retrieveBranchOrders(HeaderDTO header, String lastSyncTime,
			Date currentSyncTime);

	/**
	 * @param orderTypeDetails
	 * @return ResponseDTO
	 */
	public ResponseDTO setOrderType(OrderTypeDTO orderTypeDetails);

	/**
	 * @param labId
	 * @return OrderTypeDTO
	 */
	public OrderTypeDTO getOrderType(int labId);

	/**
	 * @param filterDTO
	 * @param user
	 * @return orderlist
	 */
	public ListResponse getByFilter(FilterDTO filterDTO, User user);

	/**
	 * @param orderId 
	 * @return List<OrderTestResultDTO>
	 */
	public List<OrderTestResultDTO> getTests(int orderId);

	/**
	 * @param orderId
	 * @return Order
	 */
	public Order getByOrderId(int orderId);

	public ListResponse getByPatientFilter(FilterDTO filterDTO, User user);

	

	public int getOrderCountByBranchId(OrderCountFilterDto ocf);

}
