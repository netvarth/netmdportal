/**
 * ResultTransferResponseDTO.java
 */
package com.nv.NetlimsPortal.rs.dto;

/**
 * @author Asha Chandran
 *
 */
public class ResultTransferResponseDTO {
	private ErrorDTO error;
	private boolean success;
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
