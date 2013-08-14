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
