/**
 * OrderTransferDTO.java
 * @author netvarth
 *
 * Version 1.0 Sep 17, 2013
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
public class OrderTransfer {
	private int sourceLabId;
	private int destinationLabId;
	private int sourceLabBranchId;
	private int destinationLabBranchId;
	private String orderDetails;
	private int uniqueId;
	private LabHeaderDTO header;
	
	
	/**
	 * @return the header
	 */
	public LabHeaderDTO getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(LabHeaderDTO header) {
		this.header = header;
	}
	/**
	 * @return the uniqueId
	 */
	public int getUniqueId() {
		return uniqueId;
	}
	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}
	/**
	 * @return the sourceLabId
	 */
	public int getSourceLabId() {
		return sourceLabId;
	}
	/**
	 * @param sourceLabId the sourceLabId to set
	 */
	public void setSourceLabId(int sourceLabId) {
		this.sourceLabId = sourceLabId;
	}
	/**
	 * @return the destinationLabId
	 */
	public int getDestinationLabId() {
		return destinationLabId;
	}
	/**
	 * @param destinationLabId the destinationLabId to set
	 */
	public void setDestinationLabId(int destinationLabId) {
		this.destinationLabId = destinationLabId;
	}
	/**
	 * @return the sourceLabBranchId
	 */
	public int getSourceLabBranchId() {
		return sourceLabBranchId;
	}
	/**
	 * @param sourceLabBranchId the sourceLabBranchId to set
	 */
	public void setSourceLabBranchId(int sourceLabBranchId) {
		this.sourceLabBranchId = sourceLabBranchId;
	}
	/**
	 * @return the destinationLabBranchId
	 */
	public int getDestinationLabBranchId() {
		return destinationLabBranchId;
	}
	/**
	 * @param destinationLabBranchId the destinationLabBranchId to set
	 */
	public void setDestinationLabBranchId(int destinationLabBranchId) {
		this.destinationLabBranchId = destinationLabBranchId;
	}
	/**
	 * @return the orderDetails
	 */
	public String getOrderDetails() {
		return orderDetails;
	}
	/**
	 * @param orderDetails the orderDetails to set
	 */
	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}
	

}
