/**
 * PasswordDTO.java
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Luciya
 *
 */
public class PasswordDTO {
	private String username;
	private String oldPassword;
	private String newPassword;
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	/**
	 * @param oldPassword the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	/**
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 */
	public PasswordDTO(String username, String oldPassword, String newPassword) {
		super();
		this.username = username;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	/**
	 * 
	 */
	public PasswordDTO() {
		
	}
	

}
