/**
 * LabBranchSystemInfoDetails.java
 * @author netvarth
 *
 * Version 1.0 Aug 19, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class BranchSystemInfoDetails {

	String criticalCpuLevel;
	String criticalMemoryLevel;
	String criticalHardDiskSpaceLevel;
	String freqType;
	String intervalTime;
	String branchName;
	float totalCpu;
	float totalHardDisk;
	float totalMemory;
	List<SystemHealthMonitorDetailList> healthMonitorList= new ArrayList<SystemHealthMonitorDetailList>();
	int branchId;
	boolean success;
	ErrorDTO error;
	
	
	/**
	 * @return the totalCpu
	 */
	public float getTotalCpu() {
		return totalCpu;
	}
	/**
	 * @param totalCpu the totalCpu to set
	 */
	public void setTotalCpu(float totalCpu) {
		this.totalCpu = totalCpu;
	}
	/**
	 * @return the totalHardDisk
	 */
	public float getTotalHardDisk() {
		return totalHardDisk;
	}
	/**
	 * @param totalHardDisk the totalHardDisk to set
	 */
	public void setTotalHardDisk(float totalHardDisk) {
		this.totalHardDisk = totalHardDisk;
	}
	/**
	 * @return the totalMemory
	 */
	public float getTotalMemory() {
		return totalMemory;
	}
	/**
	 * @param totalMemory the totalMemory to set
	 */
	public void setTotalMemory(float totalMemory) {
		this.totalMemory = totalMemory;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	public String getCriticalCpuLevel() {
		return criticalCpuLevel;
	}
	public void setCriticalCpuLevel(String criticalCpuLevel) {
		this.criticalCpuLevel = criticalCpuLevel;
	}
	public String getCriticalMemoryLevel() {
		return criticalMemoryLevel;
	}
	public void setCriticalMemoryLevel(String criticalMemoryLevel) {
		this.criticalMemoryLevel = criticalMemoryLevel;
	}
	public String getCriticalHardDiskSpaceLevel() {
		return criticalHardDiskSpaceLevel;
	}
	public void setCriticalHardDiskSpaceLevel(String criticalHardDiskSpaceLevel) {
		this.criticalHardDiskSpaceLevel = criticalHardDiskSpaceLevel;
	}
	public String getFreqType() {
		return freqType;
	}
	public void setFreqType(String freqType) {
		this.freqType = freqType;
	}
	public String getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(String intervalTime) {
		this.intervalTime = intervalTime;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the healthMonitorList
	 */
	public List<SystemHealthMonitorDetailList> getHealthMonitorList() {
		return healthMonitorList;
	}
	/**
	 * @param healthMonitorList the healthMonitorList to set
	 */
	public void setHealthMonitorList(
			List<SystemHealthMonitorDetailList> healthMonitorList) {
		this.healthMonitorList = healthMonitorList;
	}
	
	
}
