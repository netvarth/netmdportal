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
private String lastSyncTime;
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
 * @return the lastSyncTime
 */
public String getLastSyncTime() {
	return lastSyncTime;
}
/**
 * @param lastSyncTime the lastSyncTime to set
 */
public void setLastSyncTime(String lastSyncTime) {
	this.lastSyncTime = lastSyncTime;
}

}
