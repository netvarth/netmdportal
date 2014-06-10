/**
 * LabResultHeaderDTO.java
 * January 22, 2013
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Joshi
 *
 */
public class LabResultHeaderDTO {
	

	private int sourceLabId;
	private int sourceLabBranchId;	
	private String orderUid;
	private String testUId;
	private String resultDetails;
	private HeaderDTO header;
	public int getSourceLabId() {
		return sourceLabId;
	}
	public void setSourceLabId(int sourceLabId) {
		this.sourceLabId = sourceLabId;
	}
	public int getSourceLabBranchId() {
		return sourceLabBranchId;
	}
	public void setSourceLabBranchId(int sourceLabBranchId) {
		this.sourceLabBranchId = sourceLabBranchId;
	}
	public String getOrderUid() {
		return orderUid;
	}
	public void setOrderUid(String orderUid) {
		this.orderUid = orderUid;
	}
	public String getTestUId() {
		return testUId;
	}
	public void setTestUId(String testUId) {
		this.testUId = testUId;
	}
	public String getResultDetails() {
		return resultDetails;
	}
	public void setResultDetails(String resultDetails) {
		this.resultDetails = resultDetails;
	}
	public HeaderDTO getHeader() {
		return header;
	}
	public void setHeader(HeaderDTO header) {
		this.header = header;
	}
	
	

}
