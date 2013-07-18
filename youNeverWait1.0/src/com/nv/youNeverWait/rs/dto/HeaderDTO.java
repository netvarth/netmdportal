/**
 * HeaderDTO.java
 *
 * Dec 19, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;

public class HeaderDTO {
	private int netMdId;
	private int netRxId;
	private String passPhrase;
	private String macId;
	private int netMdBranchId;
	private int netRxBranchId;
	
	/**
	 * @return the netMdBranchId
	 */
	public int getNetMdBranchId() {
		return netMdBranchId;
	}

	/**
	 * @param netMdBranchId the netMdBranchId to set
	 */
	public void setNetMdBranchId(int netMdBranchId) {
		this.netMdBranchId = netMdBranchId;
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
	 * @return the netMdId
	 */
	public int getNetMdId() {
		return netMdId;
	}

	/**
	 * @param netMdId the netMdId to set
	 */
	public void setNetMdId(int netMdId) {
		this.netMdId = netMdId;
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

	public int getNetRxId() {
		return netRxId;
	}

	public void setNetRxId(int netRxId) {
		this.netRxId = netRxId;
	}

	public int getNetRxBranchId() {
		return netRxBranchId;
	}

	public void setNetRxBranchId(int netRxBranchId) {
		this.netRxBranchId = netRxBranchId;
	}

}
