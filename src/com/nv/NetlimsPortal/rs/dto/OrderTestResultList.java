/**
 * OrderTestResultList.java
 * January 23, 2013
 */
package com.nv.NetlimsPortal.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joshi
 *
 */
public class OrderTestResultList {
	private List<OrderTestResult> orderTestResultList= new ArrayList<OrderTestResult>();
	private ErrorDTO error;
	private boolean success;
	public List<OrderTestResult> getOrderTestResultList() {
		return orderTestResultList;
	}
	public void setOrderTestResultList(List<OrderTestResult> orderTestResultList) {
		this.orderTestResultList = orderTestResultList;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
