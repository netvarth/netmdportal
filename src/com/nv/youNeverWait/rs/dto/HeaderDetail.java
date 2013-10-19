/**
 * HeaderDetail.java
 * @author netvarth
 *
 * Version 1.0 Oct 17, 2013
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
public class HeaderDetail {
	private LabHeaderDTO labHeader;
	private HeaderDTO netmdHeader;
	/**
	 * @return the labHeader
	 */
	public LabHeaderDTO getLabHeader() {
		return labHeader;
	}
	/**
	 * @param labHeader the labHeader to set
	 */
	public void setLabHeader(LabHeaderDTO labHeader) {
		this.labHeader = labHeader;
	}
	/**
	 * @return the netmdHeader
	 */
	public HeaderDTO getNetmdHeader() {
		return netmdHeader;
	}
	/**
	 * @param netmdHeader the netmdHeader to set
	 */
	public void setNetmdHeader(HeaderDTO netmdHeader) {
		this.netmdHeader = netmdHeader;
	}
	
	
}
