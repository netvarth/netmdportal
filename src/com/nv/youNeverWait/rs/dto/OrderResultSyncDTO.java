/**
 * OrderResultSyncDTO.java
 * @author Mani E.V 
 *
 * Version 1.0 06-Jun-2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Mani E.V
 */
public class OrderResultSyncDTO {
	private OrderSyncDTO order;
	private Integer globalId;
	private String actionName;
	List<OrderTestResultDTO> testResult=new ArrayList<OrderTestResultDTO>();
	
	/**
	 * @return the order
	 */
	public OrderSyncDTO getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(OrderSyncDTO order) {
		this.order = order;
	}
	/**
	 * @return the globalId
	 */
	public Integer getGlobalId() {
		return globalId;
	}
	/**
	 * @param globalId the globalId to set
	 */
	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}
	/**
	 * @return the testResult
	 */
	public List<OrderTestResultDTO> getTestResult() {
		return testResult;
	}
	/**
	 * @param testResult the testResult to set
	 */
	public void setTestResult(List<OrderTestResultDTO> testResult) {
		this.testResult = testResult;
	}
	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
}
