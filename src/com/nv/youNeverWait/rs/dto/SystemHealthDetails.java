/**
 * SystemHealthMonitor.java
 * @author netvarth
 *
 * Version 1.0 Aug 8, 2013
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
public class SystemHealthDetails {
	LabHeaderDTO header;
	String hardDiskUsed;
	String memoryUsed;
	String cpuUsage;
	String freqType;
	String intervalTime;
	String totalHardDiskSpace;
	String totalMemorySpace;
	String totalCpuSpace;
	

	
	public String getTotalCpuSpace() {
		return totalCpuSpace;
	}
	public void setTotalCpuSpace(String totalCpuSpace) {
		this.totalCpuSpace = totalCpuSpace;
	}
	public String getTotalHardDiskSpace() {
		return totalHardDiskSpace;
	}
	public void setTotalHardDiskSpace(String totalHardDiskSpace) {
		this.totalHardDiskSpace = totalHardDiskSpace;
	}
	public String getTotalMemorySpace() {
		return totalMemorySpace;
	}
	public void setTotalMemorySpace(String totalMemorySpace) {
		this.totalMemorySpace = totalMemorySpace;
	}
	
	public String getFreqType() {
		return freqType;
	}
	public void setFreqType(String freqType) {
		this.freqType = freqType;
	}
	/**
	 * @return the intervalTime
	 */
	public String getIntervalTime() {
		return intervalTime;
	}
	/**
	 * @param intervalTime the intervalTime to set
	 */
	public void setIntervalTime(String intervalTime) {
		this.intervalTime = intervalTime;
	}
	/**
	 * @return the cpuUsage
	 */
	public String getCpuUsage() {
		return cpuUsage;
	}
	/**
	 * @param cpuUsage the cpuUsage to set
	 */
	public void setCpuUsage(String cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

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
	 * @return the hardDiskUsed
	 */
	public String getHardDiskUsed() {
		return hardDiskUsed;
	}
	/**
	 * @param hardDiskUsed the hardDiskUsed to set
	 */
	public void setHardDiskUsed(String hardDiskUsed) {
		this.hardDiskUsed = hardDiskUsed;
	}
	/**
	 * @return the memoryUsed
	 */
	public String getMemoryUsed() {
		return memoryUsed;
	}
	/**
	 * @param memoryUsed the memoryUsed to set
	 */
	public void setMemoryUsed(String memoryUsed) {
		this.memoryUsed = memoryUsed;
	}

}
