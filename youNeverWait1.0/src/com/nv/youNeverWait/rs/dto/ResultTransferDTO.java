/**
 * ResultTransferDTO.java
 * @author Asha Chandran
 *
 */
package com.nv.youNeverWait.rs.dto;


public class ResultTransferDTO {
	private HeaderDTO header;
	private int sourceLabId;
	private int destinationLabId;
	private int sourceBranchId;
	private int destinationBranchId;
	private String result;
	private String orderUid;
	
	
	/**
	 * @return the sourceLabId
	 */
	public int getSourceLabId() {
		return sourceLabId;
	}
	/**
	 * @param sourceLabId the sourceLabId to set
	 */
	public void setSourceLabId(int sourceLabId) {
		this.sourceLabId = sourceLabId;
	}
	/**
	 * @return the destinationLabId
	 */
	public int getDestinationLabId() {
		return destinationLabId;
	}
	/**
	 * @param destinationLabId the destinationLabId to set
	 */
	public void setDestinationLabId(int destinationLabId) {
		this.destinationLabId = destinationLabId;
	}
	/**
	 * @return the sourceBranchId
	 */
	public int getSourceBranchId() {
		return sourceBranchId;
	}
	/**
	 * @param sourceBranchId the sourceBranchId to set
	 */
	public void setSourceBranchId(int sourceBranchId) {
		this.sourceBranchId = sourceBranchId;
	}
	/**
	 * @return the destinationBranchId
	 */
	public int getDestinationBranchId() {
		return destinationBranchId;
	}
	/**
	 * @param destinationBranchId the destinationBranchId to set
	 */
	public void setDestinationBranchId(int destinationBranchId) {
		this.destinationBranchId = destinationBranchId;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the orderUid
	 */
	public String getOrderUid() {
		return orderUid;
	}
	/**
	 * @param orderUid the orderUid to set
	 */
	public void setOrderUid(String orderUid) {
		this.orderUid = orderUid;
	}
	/**
	 * @return the header
	 */
	public HeaderDTO getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(HeaderDTO header) {
		this.header = header;
	}
	

}
