/**
 * SyncFreqDTO.java
 * @author netvarth
 *
 * Version 1.0 Oct 7, 2013
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
public class SyncFreqDTO {
	private boolean enableSync;
	private int syncTime;
	private String syncFreqType;
	private int labId;
	private int labBranchId;
	private int netmdId;
	private int netmdBranchId;
	private boolean success;
	private ErrorDTO error;
	
	
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
	 * @return the labId
	 */
	public int getLabId() {
		return labId;
	}
	/**
	 * @param labId the labId to set
	 */
	public void setLabId(int labId) {
		this.labId = labId;
	}
	/**
	 * @return the labBranchId
	 */
	public int getLabBranchId() {
		return labBranchId;
	}
	/**
	 * @param labBranchId the labBranchId to set
	 */
	public void setLabBranchId(int labBranchId) {
		this.labBranchId = labBranchId;
	}
	/**
	 * @return the netmdId
	 */
	public int getNetmdId() {
		return netmdId;
	}
	/**
	 * @param netmdId the netmdId to set
	 */
	public void setNetmdId(int netmdId) {
		this.netmdId = netmdId;
	}
	/**
	 * @return the netmdBranchId
	 */
	public int getNetmdBranchId() {
		return netmdBranchId;
	}
	/**
	 * @param netmdBranchId the netmdBranchId to set
	 */
	public void setNetmdBranchId(int netmdBranchId) {
		this.netmdBranchId = netmdBranchId;
	}
	/**
	 * @return the enableSync
	 */
	public boolean isEnableSync() {
		return enableSync;
	}
	/**
	 * @param enableSync the enableSync to set
	 */
	public void setEnableSync(boolean enableSync) {
		this.enableSync = enableSync;
	}
	/**
	 * @return the syncTime
	 */
	public int getSyncTime() {
		return syncTime;
	}
	/**
	 * @param syncTime the syncTime to set
	 */
	public void setSyncTime(int syncTime) {
		this.syncTime = syncTime;
	}
	/**
	 * @return the syncFreqType
	 */
	public String getSyncFreqType() {
		return syncFreqType;
	}
	/**
	 * @param syncFreqType the syncFreqType to set
	 */
	public void setSyncFreqType(String syncFreqType) {
		this.syncFreqType = syncFreqType;
	}
	

}
