/**
 * HealthDetail.java
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
public class SystemHealthDetail {
	long freeHardDiskSpaceInPercent;
	long freeMemorySpaceInPercent;
	long freeCpuSpaceInPercent;
	float criticalHardDiskLevel;
	float criticalCpuLevel;
	float criticalMemoryLevel;
	long totalCpuUsage;
	long totalMemoryUsed;
	long totalHardDiskUsed;
	int intervalTime;
	String freqType;
	int branchId;
	String passPhrase;
	String appType;
	
	/**
	 * @return the totalCpuUsage
	 */
	public long getTotalCpuUsage() {
		return totalCpuUsage;
	}
	/**
	 * @param totalCpuUsage the totalCpuUsage to set
	 */
	public void setTotalCpuUsage(long totalCpuUsage) {
		this.totalCpuUsage = totalCpuUsage;
	}
	/**
	 * @return the totalMemoryUsed
	 */
	public long getTotalMemoryUsed() {
		return totalMemoryUsed;
	}
	/**
	 * @param totalMemoryUsed the totalMemoryUsed to set
	 */
	public void setTotalMemoryUsed(long totalMemoryUsed) {
		this.totalMemoryUsed = totalMemoryUsed;
	}
	/**
	 * @return the totalHardDiskUsed
	 */
	public long getTotalHardDiskUsed() {
		return totalHardDiskUsed;
	}
	/**
	 * @param totalHardDiskUsed the totalHardDiskUsed to set
	 */
	public void setTotalHardDiskUsed(long totalHardDiskUsed) {
		this.totalHardDiskUsed = totalHardDiskUsed;
	}
	/**
	 * @return the intervalTime
	 */
	public int getIntervalTime() {
		return intervalTime;
	}
	/**
	 * @param intervalTime the intervalTime to set
	 */
	public void setIntervalTime(int intervalTime) {
		this.intervalTime = intervalTime;
	}
	
	/**
	 * @return the freqType
	 */
	public String getFreqType() {
		return freqType;
	}
	/**
	 * @param freqType the freqType to set
	 */
	public void setFreqType(String freqType) {
		this.freqType = freqType;
	}
	
	
	/**
	 * @return the criticalHardDiskLevel
	 */
	public float getCriticalHardDiskLevel() {
		return criticalHardDiskLevel;
	}
	/**
	 * @param criticalHardDiskLevel the criticalHardDiskLevel to set
	 */
	public void setCriticalHardDiskLevel(float criticalHardDiskLevel) {
		this.criticalHardDiskLevel = criticalHardDiskLevel;
	}
	/**
	 * @return the criticalCpuLevel
	 */
	public float getCriticalCpuLevel() {
		return criticalCpuLevel;
	}
	/**
	 * @param criticalCpuLevel the criticalCpuLevel to set
	 */
	public void setCriticalCpuLevel(float criticalCpuLevel) {
		this.criticalCpuLevel = criticalCpuLevel;
	}
	/**
	 * @return the criticalMemoryLevel
	 */
	public float getCriticalMemoryLevel() {
		return criticalMemoryLevel;
	}
	/**
	 * @param criticalMemoryLevel the criticalMemoryLevel to set
	 */
	public void setCriticalMemoryLevel(float criticalMemoryLevel) {
		this.criticalMemoryLevel = criticalMemoryLevel;
	}
	/**
	 * @return the passPhrase
	 */
	public String getPassPhrase() {
		return passPhrase;
	}
	/**
	 * @param passPhrase the passPhrase to set
	 */
	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}
	/**
	 * @return the appType
	 */
	public String getAppType() {
		return appType;
	}
	/**
	 * @param appType the appType to set
	 */
	public void setAppType(String appType) {
		this.appType = appType;
	}
	/**
	 * @return the freeHardDiskSpaceInPercent
	 */
	public long getFreeHardDiskSpaceInPercent() {
		return freeHardDiskSpaceInPercent;
	}
	/**
	 * @param freeHardDiskSpaceInPercent the freeHardDiskSpaceInPercent to set
	 */
	public void setFreeHardDiskSpaceInPercent(long freeHardDiskSpaceInPercent) {
		this.freeHardDiskSpaceInPercent = freeHardDiskSpaceInPercent;
	}
	/**
	 * @return the freeMemorySpaceInPercent
	 */
	public long getFreeMemorySpaceInPercent() {
		return freeMemorySpaceInPercent;
	}
	/**
	 * @param freeMemorySpaceInPercent the freeMemorySpaceInPercent to set
	 */
	public void setFreeMemorySpaceInPercent(long freeMemorySpaceInPercent) {
		this.freeMemorySpaceInPercent = freeMemorySpaceInPercent;
	}
	/**
	 * @return the freeCpuSpaceInPercent
	 */
	public long getFreeCpuSpaceInPercent() {
		return freeCpuSpaceInPercent;
	}
	/**
	 * @param freeCpuSpaceInPercent the freeCpuSpaceInPercent to set
	 */
	public void setFreeCpuSpaceInPercent(long freeCpuSpaceInPercent) {
		this.freeCpuSpaceInPercent = freeCpuSpaceInPercent;
	}
	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

}
