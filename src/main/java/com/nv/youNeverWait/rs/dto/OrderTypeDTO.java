/**
 * OrderTypeDTO.java
 * @author netvarth
 *
 * Version 1.0 Oct 1, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

/**
 *
 *
 * @author Luciya Jose
 */
public class OrderTypeDTO {
	private String orderTypeCodes;
	private int labId;
	private ErrorDTO error;
	private boolean success;
	
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
	/**
	 * @return the orderTypeCodes
	 */
	public String getOrderTypeCodes() {
		return orderTypeCodes;
	}
	/**
	 * @param orderTypeCodes the orderTypeCodes to set
	 */
	public void setOrderTypeCodes(String orderTypeCodes) {
		this.orderTypeCodes = orderTypeCodes;
	}
	/**
	 * @return the labId
	 */
	public int getLabId() {
		return labId;
	}
	/**
	 * @param labId the labId to set
	 */
	public void setLabId(int labId) {
		this.labId = labId;
	}
	

}
