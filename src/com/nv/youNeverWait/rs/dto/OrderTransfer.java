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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import com.nv.youNeverWait.util.filter.core.JsonUtil;

/**
 *
 *
 * @author Luciya Jose
 */
public class OrderTransfer {
	private int sourceLabId;
	private int destinationLabId;
	private int sourceLabBranchId;
	//private int destinationLabBranchId;
	private List<Integer> destinationBranches= new ArrayList<Integer>();
	private Object orderDetails;
	private String orderUid;
	private HeaderDTO header;
	
	
	public List<Integer> getDestinationBranches() {
		return destinationBranches;
	}
	public void setDestinationBranches(List<Integer> destinationBranches) {
		this.destinationBranches = destinationBranches;
	}
	/**
	 * @return the header
	 */
	public HeaderDTO getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(HeaderDTO header) {
		this.header = header;
	}
	
	/**
	 * @return the orderUid
	 */
	public String getOrderUid() {
		return orderUid;
	}
	/**
	 * @param orderUid the orderUid to set
	 */
	public void setOrderUid(String orderUid) {
		this.orderUid = orderUid;
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
//	/**
//	 * @return the destinationLabBranchId
//	 */
//	public int getDestinationLabBranchId() {
//		return destinationLabBranchId;
//	}
//	/**
//	 * @param destinationLabBranchId the destinationLabBranchId to set
//	 */
//	public void setDestinationLabBranchId(int destinationLabBranchId) {
//		this.destinationLabBranchId = destinationLabBranchId;
//	}
	/**
	 * @return the orderDetails
	 */
	public String getOrderDetails() {
		
		return (String) orderDetails;
	}
	/**
	 * @param orderDetails the orderDetails to set
	 */
	public void setOrderDetails(Object orderDetails) {
		this.orderDetails = JsonUtil.getString(orderDetails);
	}
	
	public void setTransferOrderDetails(String order) {
		this.orderDetails = JsonUtil.getObject(order);
	}
	

}
