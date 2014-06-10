/**
 * UserDTO.java
 *
 * Jan 2, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * 
 */
public class UserDTO {
	private UserInfoDetail userDetail;
	private HeaderDTO header;
	
	
	/**
	 * @return the userDetail
	 */
	public UserInfoDetail getUserDetail() {
		return userDetail;
	}
	/**
	 * @param userDetail the userDetail to set
	 */
	public void setUserDetail(UserInfoDetail userDetail) {
		this.userDetail = userDetail;
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
	 * @param userDetail
	 * @param header
	 */
	public UserDTO(UserInfoDetail userDetail, HeaderDTO header) {
		super();
		this.userDetail = userDetail;
		this.header = header;
	}
	/**
	 * 
	 */
	public UserDTO() {
		
	}
	
	
}
