/**
 * HealthMonitorResponse.java
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
public class HealthMonitorResponse {
	String freqPeriod;
	 String intervalTime;
	 ErrorDTO error;
	 boolean success;
	 boolean critical;
	 long freeHardDiskSpaceInPercent;
	 long freeMemorySpaceInPercent;
	 long freeCpuSpaceInPercent;
	
	 
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
	 * @return the critical
	 */
	public boolean isCritical() {
		return critical;
	}
	/**
	 * @param critical the critical to set
	 */
	public void setCritical(boolean critical) {
		this.critical = critical;
	}
	/**
	 * @return the freqPeriod
	 */
	public String getFreqPeriod() {
		return freqPeriod;
	}
	/**
	 * @param freqPeriod the freqPeriod to set
	 */
	public void setFreqPeriod(String freqPeriod) {
		this.freqPeriod = freqPeriod;
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
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * 
	 */
	public HealthMonitorResponse() {
		
	}

}
