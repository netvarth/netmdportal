/**
 * UserRetrievalDTO.java
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Luciya
 *
 */
public class UserRetrievalDTO {
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
	/**
	 * 
	 */
	public UserRetrievalDTO() {
		
	}
	/**
	 * @param header
	 * @param synctime
	 */
	public UserRetrievalDTO(HeaderDTO header, String synctime) {
		super();
		this.header = header;
		this.synctime = synctime;
	}
	
	
}
