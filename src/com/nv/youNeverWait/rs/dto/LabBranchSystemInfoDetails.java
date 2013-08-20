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

public class LabBranchSystemInfoDetails {

	String criticalCpuLevel;
	String criticalMemoryLevel;
	String criticalHardDiskSpaceLevel;
	String freqType;
	String intervalTime;
	String branchName;
	String currentCpuUsage;
	String currentHardDiskSpace;
	String currentMemorySpace;
	int branchId;
	boolean success;
	ErrorDTO error;
	
	
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
	public String getCurrentCpuUsage() {
		return currentCpuUsage;
	}
	public void setCurrentCpuUsage(String currentCpuUsage) {
		this.currentCpuUsage = currentCpuUsage;
	}
	public String getCurrentHardDiskSpace() {
		return currentHardDiskSpace;
	}
	public void setCurrentHardDiskSpace(String currentHardDiskSpace) {
		this.currentHardDiskSpace = currentHardDiskSpace;
	}
	public String getCurrentMemorySpace() {
		return currentMemorySpace;
	}
	public void setCurrentMemorySpace(String currentMemorySpace) {
		this.currentMemorySpace = currentMemorySpace;
	}
	
}
