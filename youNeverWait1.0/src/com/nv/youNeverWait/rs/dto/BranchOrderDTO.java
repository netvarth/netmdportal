/**
 * BranchOrderDTO.java
 *
 * @Author Luciya Jos
 * Jun 25, 2013 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author netvarth
 *
 */
public class BranchOrderDTO {
	private String fromDate;
	private String toDate;
	private int labId;
	private int labBranchId;

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
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
}
