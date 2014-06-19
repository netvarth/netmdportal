/**
 * LabBranchRetrievalDTO.java
 * @author Asha Chandran
 */
package com.nv.NetlimsPortal.rs.dto;

public class LabBranchRetrievalDTO {

	private HeaderDTO header;
	private String synctime;
	
	
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
