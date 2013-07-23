/**
 * ResultRetrievalDTO.java
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Asha Chandran
 *
 */
public class ResultRetrievalDTO {
	private LabHeaderDTO header;
	private int labId;
	private int branchId;
	private String syncTime;
	
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
	/**
	 * @return the syncTime
	 */
	public String getSyncTime() {
		return syncTime;
	}
	/**
	 * @param syncTime the syncTime to set
	 */
	public void setSyncTime(String syncTime) {
		this.syncTime = syncTime;
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

}
