/**
 * OrderDetails.java
 * @author netvarth
 *
 * Version 1.0 Sep 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.NetlimsPortal.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Luciya Jose
 */

public class OrderDetails {
	
	
	private List<Object> orders= new ArrayList<Object>();
	private List<OrderTestResult> orderTestResult = new ArrayList<OrderTestResult>();
	private String lastOrderSyncTime;
	private ErrorDTO error;
	private boolean success;
	
	
	
	public List<OrderTestResult> getOrderTestResult() {
		return orderTestResult;
	}
	public void setOrderTestResult(List<OrderTestResult> orderTestResult) {
		this.orderTestResult = orderTestResult;
	}
	/**
	 * @return the lastOrderSyncTime
	 */
	public String getLastOrderSyncTime() {
		return lastOrderSyncTime;
	}
	/**
	 * @param lastOrderSyncTime the lastOrderSyncTime to set
	 */
	public void setLastOrderSyncTime(String lastOrderSyncTime) {
		this.lastOrderSyncTime = lastOrderSyncTime;
	}
	
	/**
	 * @return the orders
	 */
	public List<Object> getOrders() {
		return orders;
	}
	/**
	 * @param orders the orders to set
	 */
	public void setOrders(List<Object> orders) {
		this.orders = orders;
	}
	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	

}
