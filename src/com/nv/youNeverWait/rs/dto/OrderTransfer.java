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

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;

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
	private Object orderDetails;
	private String orderUid;
	private HeaderDTO header;
	
	
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
		
		return (String) orderDetails;
	}
	/**
	 * @param orderDetails the orderDetails to set
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public void setOrderDetails(Object orderDetails) {
		ObjectMapper maper = new ObjectMapper();
		String jsonRequest = null;
		try {
			jsonRequest = maper.writeValueAsString(orderDetails);
		} catch (Exception e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		this.orderDetails = jsonRequest;

	}
	
	public void setTransferOrderDetails(String order) {
		ObjectMapper maper = new ObjectMapper();
		Object orderDetails;
		try {
			orderDetails = maper.readValue(order, Object.class);
		} catch (Exception e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		this.orderDetails = orderDetails;

	}
	

}
