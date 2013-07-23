/**
 * NetLimsSyncDTO.java
 *
 * @Author Luciya Jos
 * May 10, 2013 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Luciya Jos
 *
 */
public class LabSyncDTO {
	private LabHeaderDTO header;
	private String lastSyncTime;
	private BranchOrderDetail branchOrders;
	
	
	/**
	 * @return the branchOrders
	 */
	public BranchOrderDetail getBranchOrders() {
		return branchOrders;
	}
	/**
	 * @param branchOrders the branchOrders to set
	 */
	public void setBranchOrders(BranchOrderDetail branchOrders) {
		this.branchOrders = branchOrders;
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
	/**
	 * @return the lastSyncTime
	 */
	public String getLastSyncTime() {
		return lastSyncTime;
	}
	/**
	 * @param lastSyncTime the lastSyncTime to set
	 */
	public void setLastSyncTime(String lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}
	

}
