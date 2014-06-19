/**
 * NetLimsSyncDTO.java
 *
 * @Author Luciya Jos
 * May 10, 2013 
 */
package com.nv.NetlimsPortal.rs.dto;

/**
 * @author Luciya Jos
 *
 */
public class LabSyncDTO {
	private HeaderDTO header;
	private String lastSyncTime;
	private BranchOrderDetail branchOrders;
//	private String freqType;
//	private int interval;
	
//	/**
//	 * @return the freqType
//	 */
//	public String getFreqType() {
//		return freqType;
//	}
//	/**
//	 * @param freqType the freqType to set
//	 */
//	public void setFreqType(String freqType) {
//		this.freqType = freqType;
//	}
//	/**
//	 * @return the interval
//	 */
//	public int getInterval() {
//		return interval;
//	}
//	/**
//	 * @param interval the interval to set
//	 */
//	public void setInterval(int interval) {
//		this.interval = interval;
//	}
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
	public HeaderDTO getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(HeaderDTO header) {
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
