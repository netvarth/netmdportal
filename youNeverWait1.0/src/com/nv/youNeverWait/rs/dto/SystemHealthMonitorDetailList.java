/**
 * SystemHealthMonitorDetailList.java
 * @author netvarth
 *
 * Version 1.0 Aug 23, 2013
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
public class SystemHealthMonitorDetailList {
	float cpuUsage;
	float memoryUsage;
	float hardDiskSpaceUasge;
	String createdDateTime;
	/**
	 * @return the cpuUsage
	 */
	public float getCpuUsage() {
		return cpuUsage;
	}
	/**
	 * @param cpuUsage the cpuUsage to set
	 */                                                                                          
	public void setCpuUsage(float cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	/**
	 * @return the memoryUsage
	 */
	public float getMemoryUsage() {
		return memoryUsage;
	}
	/**
	 * @param memoryUsage the memoryUsage to set
	 */
	public void setMemoryUsage(float memoryUsage) {
		this.memoryUsage = memoryUsage;
	}
	/**
	 * @return the hardDiskSpaceUasge
	 */
	public float getHardDiskSpaceUasge() {
		return hardDiskSpaceUasge;
	}
	/**
	 * @param hardDiskSpaceUasge the hardDiskSpaceUasge to set
	 */
	public void setHardDiskSpaceUasge(float hardDiskSpaceUasge) {
		this.hardDiskSpaceUasge = hardDiskSpaceUasge;
	}
	/**
	 * @return the createdDateTime
	 */
	public String getCreatedDateTime() {
		return createdDateTime;
	}
	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	
	
}
