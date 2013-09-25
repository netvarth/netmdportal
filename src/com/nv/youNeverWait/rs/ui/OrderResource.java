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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.OrderTransfer;
import com.nv.youNeverWait.rs.dto.OrderTransferResponse;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.user.bl.service.OrderManager;

/**
 *
 *
 * @author Luciya Jose
 */

@Controller
@RequestMapping("ui/order/")
public class OrderResource {
	private OrderManager orderManager;

	@RequestMapping(value = "orderTransfer", method = RequestMethod.POST)
	@ResponseBody
	public OrderTransferResponse orderTransfer(
			@RequestBody OrderTransfer orderTranfer) {
		OrderTransferResponse response = new OrderTransferResponse();
		try {
			response = orderManager.transferOrder(orderTranfer);
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
	 * @return the orderManager
	 */
	public OrderManager getOrderManager() {
		return orderManager;
	}

	/**
	 * @param orderManager the orderManager to set
	 */
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}
	
}
