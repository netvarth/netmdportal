/**
 * HeaderDTO.java
 *
 * Dec 19, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;

public class LabHeaderDTO {
	private int labId;
	private String passPhrase;
	private String macId;
	private int labBranchId;
	
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

}
