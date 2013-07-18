/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author netvarth
 *
 */
public class UserRetrievalDTO {
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
	/**
	 * 
	 */
	public UserRetrievalDTO() {
		
	}
	/**
	 * @param header
	 * @param synctime
	 */
	public UserRetrievalDTO(LabHeaderDTO header, String synctime) {
		super();
		this.header = header;
		this.synctime = synctime;
	}
	
	
}
