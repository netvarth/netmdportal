/**
 * NetMdResponseDTO.java
 */
package com.nv.youNeverWait.rs.dto;

public class NetMdResponseDTO {
	private ErrorDTO error;
	private boolean success;
	private boolean existMac;
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
	 * @return the existMac
	 */
	public boolean isExistMac() {
		return existMac;
	}
	/**
	 * @param existMac the existMac to set
	 */
	public void setExistMac(boolean existMac) {
		this.existMac = existMac;
	}
	
	

}
