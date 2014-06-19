/**
 * LabResponseDTO.java
 *
 * Jan 16, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.NetlimsPortal.rs.dto;



/**
 * 
 */
public class LabResponseDTO {

	private LabDTO lab ;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the lab
	 */
	public LabDTO getLab() {
		return lab;
	}
	/**
	 * @param lab the lab to set
	 */
	public void setLab(LabDTO lab) {
		this.lab = lab;
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
}
