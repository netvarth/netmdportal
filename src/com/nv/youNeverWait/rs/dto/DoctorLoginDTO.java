/**
 * DoctorLoginDTO.java
 *
 * @Author Luciya Jos
 * May 29, 2013 
 */
package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.pl.entity.NetmdDoctorTbl;

/**
 * @author Luciya Jos
 *
 */
public class DoctorLoginDTO {
	private String password;
	private int doctorGlobalId;
	private String email;
	private ErrorDTO error;
	private boolean success;
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the doctorGlobalId
	 */
	public int getDoctorGlobalId() {
		return doctorGlobalId;
	}
	/**
	 * @param doctorGlobalId the doctorGlobalId to set
	 */
	public void setDoctorGlobalId(int doctorGlobalId) {
		this.doctorGlobalId = doctorGlobalId;
	}
	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @param doc 
	 * @param password
	 * @param doctorGlobalId
	 */
	public DoctorLoginDTO(NetmdDoctorTbl doc) {
	
		this.password = doc.getLoginTbl().getPassword();
		this.doctorGlobalId = doc.getId();
		this.email=doc.getEmail();
		this.success=true;
	}
	

}
