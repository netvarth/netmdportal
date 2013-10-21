package com.nv.youNeverWait.rs.dto;
/**
 * @author Ashly pauly
 *
 */
public class BranchBillListDTO {
	private String fromDate;
	private String toDate;
	private int netmdId;
	private int netmdBranchId;
	
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
	
}
