/**
 * OrderResource.java
 * @author netvarth
 *
 * Version 1.0 Sep 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.BranchOrderDTO;
import com.nv.youNeverWait.rs.dto.BranchOrdersResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.HealthMonitorResponse;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTransferResponse;
import com.nv.youNeverWait.rs.dto.OrderTypeDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;
import com.nv.youNeverWait.user.bl.service.OrderService;

/**
 * 
 * 
 * @author Luciya Jose
 */

@Controller
@RequestMapping("ui/order/")
public class OrderResource {
	private OrderService orderService;

	

	/**
	 * Method performed for nomenclature of order types
	 * 
	 * @return HealthMonitorResponse
	 */
	@RequestMapping(value = "setOrderType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO setOrderType(
			@RequestBody OrderTypeDTO orderTypeDetails) {

		ResponseDTO response = new ResponseDTO();
		try {
			response = orderService.setOrderType(orderTypeDetails);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * Method performed for viewing order types
	 * 
	 * @return HealthMonitorResponse
	 */
	@RequestMapping(value = "getOrderType/{labId}", method = RequestMethod.GET)
	@ResponseBody
	public OrderTypeDTO getOrderType(
			@PathVariable int labId) {

		OrderTypeDTO response = new OrderTypeDTO();
		try {
			response = orderService.getOrderType(labId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * @return the orderService
	 */
	public OrderService getOrderService() {
		return orderService;
	}

	/**
	 * @param orderService
	 *            the orderService to set
	 */
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

}
