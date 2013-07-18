/**
 * NetRxHeaderDTO.java
 *
 * @Author Luciya Jos
 * May 21, 2013 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author netvarth
 *
 */
public class NetRxHeaderDTO {
	private int netRxId;
	private String passPhrase;
	private String macId;
	private int netRxBranchId;
	/**
	 * @return the netRxId
	 */
	public int getNetRxId() {
		return netRxId;
	}
	/**
	 * @param netRxId the netRxId to set
	 */
	public void setNetRxId(int netRxId) {
		this.netRxId = netRxId;
	}
	/**
	 * @return the passPhrase
	 */
	public String getPassPhrase() {
		return passPhrase;
	}
	/**
	 * @param passPhrase the passPhrase to set
	 */
	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}
	/**
	 * @return the macId
	 */
	public String getMacId() {
		return macId;
	}
	/**
	 * @param macId the macId to set
	 */
	public void setMacId(String macId) {
		this.macId = macId;
	}
	/**
	 * @return the netRxBranchId
	 */
	public int getNetRxBranchId() {
		return netRxBranchId;
	}
	/**
	 * @param netRxBranchId the netRxBranchId to set
	 */
	public void setNetRxBranchId(int netRxBranchId) {
		this.netRxBranchId = netRxBranchId;
	}
	

}
