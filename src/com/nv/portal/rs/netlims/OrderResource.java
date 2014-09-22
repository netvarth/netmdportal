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
package com.nv.portal.rs.netlims;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nv.security.youNeverWait.User;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.exception.ServiceExceptionHandler;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.ListResponse;
import com.nv.youNeverWait.rs.dto.Order;
import com.nv.youNeverWait.rs.dto.OrderCountFilterDto;
import com.nv.youNeverWait.rs.dto.OrderTestResultDTO;
import com.nv.youNeverWait.rs.dto.OrderTypeDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.user.bl.service.OrderService;

/**
 * 
 * 
 * @author Luciya Jose
 */

@Controller
@RequestMapping("ui/order")
public class OrderResource extends ServiceExceptionHandler{
	private OrderService orderService;

	/**
	 * for listing orders
	 * @param filterDTO
	 * @return OrderListResponseDTO
	 * @throws ServiceException
	 * @throws RuntimeException
	 */
	@RequestMapping(value = "/facility/getByFilter", method = RequestMethod.POST)
	@ResponseBody
	public ListResponse list(@RequestBody FilterDTO filterDTO)throws ServiceException, RuntimeException{
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user=(User) req.getSession().getAttribute("user");	
		return orderService.getByFilter(filterDTO,user);
	}
	@RequestMapping(value = "/patient/getByFilter", method = RequestMethod.POST)
	@ResponseBody
	public ListResponse getByPatient(@RequestBody FilterDTO filterDTO)throws ServiceException, RuntimeException{
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user=(User) req.getSession().getAttribute("user");	
		return orderService.getByPatientFilter(filterDTO,user);
	}
	/**
	 * @param orderId
	 * @return Order
	 */
	@RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
	@ResponseBody
	public Order getOrder(@PathVariable int orderId){
		return orderService.getByOrderId(orderId);
		
	}
	
	/**
	 * Method to get count of orders transferred from a branch to the portal
	 */
	/**
	 * @param branchId
	 * @return Order
	 */
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	@ResponseBody
	public int getOrderCount(@RequestBody OrderCountFilterDto ocf){
		System.out.println(ocf.getFromDate());
		return orderService.getOrderCountByBranchId(ocf);
		
	}

	/**
	 * Method performed for nomenclature of order types
	 * @param orderTypeDetails 
	 * 
	 * @return HealthMonitorResponse
	 */
	@RequestMapping(value = "/setOrderType", method = RequestMethod.POST)
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
	 * @param orderId
	 * @param branchId
	 * @return orderTest List
	 */
	@RequestMapping(value="/getTests/{orderId}", method = RequestMethod.GET)
	@ResponseBody
	public List<OrderTestResultDTO> getTests(@PathVariable int orderId){
		return orderService.getTests(orderId);
	}
	/**
	 * Method performed for viewing order types
	 * @param labId 
	 * 
	 * @return HealthMonitorResponse
	 */
	@RequestMapping(value = "/getOrderType/{labId}", method = RequestMethod.GET)
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
