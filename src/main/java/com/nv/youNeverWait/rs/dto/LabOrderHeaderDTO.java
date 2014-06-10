/**
 * RetreiveOrderDTO.java
 * @author netvarth
 *
 * Version 1.0 Nov 28, 2013
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
public class LabOrderHeaderDTO {
private HeaderDTO header;
private String lastOrderSyncTime;
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


}
