/**
 * EnableLogStatusResponseDTO.java
 *
 * @Author Luciya Jos
 * Jun 21, 2013 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author netvarth
 *
 */
public class EnableLogStatusResponseDTO {
	private boolean status;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
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
