/**
 * LabBranchRetrievalDTO.java
 * @author Asha Chandran
 */
package com.nv.youNeverWait.rs.dto;

public class LabBranchRetrievalDTO {

	private LabHeaderDTO header;
	private String synctime;
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
	 * @return the synctime
	 */
	public String getSynctime() {
		return synctime;
	}
	/**
	 * @param synctime the synctime to set
	 */
	public void setSynctime(String synctime) {
		this.synctime = synctime;
	}
	
	
	
}
