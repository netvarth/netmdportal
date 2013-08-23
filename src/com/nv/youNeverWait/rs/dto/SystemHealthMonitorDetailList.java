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
	int cpuUsage;
	int memoryUsage;
	int hardDiskSpaceUasge;
	String createdDateTime;
	
	/**
	 * @return the cpuUsage
	 */
	public int getCpuUsage() {
		return cpuUsage;
	}
	/**
	 * @param cpuUsage the cpuUsage to set
	 */
	public void setCpuUsage(int cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	/**
	 * @return the memoryUsage
	 */
	public int getMemoryUsage() {
		return memoryUsage;
	}
	/**
	 * @param memoryUsage the memoryUsage to set
	 */
	public void setMemoryUsage(int memoryUsage) {
		this.memoryUsage = memoryUsage;
	}
	/**
	 * @return the hardDiskSpaceUasge
	 */
	public int getHardDiskSpaceUasge() {
		return hardDiskSpaceUasge;
	}
	/**
	 * @param hardDiskSpaceUasge the hardDiskSpaceUasge to set
	 */
	public void setHardDiskSpaceUasge(int hardDiskSpaceUasge) {
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
