/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author nv
 *
 */
public class CreatePasswordDTO {
	private String password;
	private String confirmPassword;
	private String username;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
