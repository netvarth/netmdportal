/**
 * ResultRetrievalResponseDTO.java
 */
package com.nv.NetlimsPortal.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Asha Chandran
 *
 */
public class ResultRetrievalResponseDTO {
	private List<ResultTransferDTO> result = new ArrayList<ResultTransferDTO>();
	private String lastSyncTime;
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
	/**
	 * @return the result
	 */
	public List<ResultTransferDTO> getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(List<ResultTransferDTO> result) {
		this.result = result;
	}
	/**
	 * @return the lastSyncTime
	 */
	public String getLastSyncTime() {
		return lastSyncTime;
	}
	/**
	 * @param lastSyncTime the lastSyncTime to set
	 */
	public void setLastSyncTime(String lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}
	
	
	
}
