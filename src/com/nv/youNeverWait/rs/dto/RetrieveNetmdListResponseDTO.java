/**
 * RetrieveNetmdListResponseDTO.java
 * 
 * Mar 7, 2013
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luciya
 *
 */
public class RetrieveNetmdListResponseDTO {
	private	List<NetMdDTO> newNetmdList = new ArrayList<NetMdDTO>();
	private List<NetMdDTO> updateNetmdList = new ArrayList<NetMdDTO>();
	private String lastSynctime;
	private boolean success;
	private ErrorDTO error;
	
	/**
	 * @return the lastSynctime
	 */
	public String getLastSynctime() {
		return lastSynctime;
	}
	/**
	 * @param lastSynctime the lastSynctime to set
	 */
	public void setLastSynctime(String lastSynctime) {
		this.lastSynctime = lastSynctime;
	}
	/**
	 * @return the newNetmdList
	 */
	public List<NetMdDTO> getNewNetmdList() {
		return newNetmdList;
	}
	/**
	 * @param newNetmdList the newNetmdList to set
	 */
	public void setNewNetmdList(List<NetMdDTO> newNetmdList) {
		this.newNetmdList = newNetmdList;
	}
	/**
	 * @return the updateNetmdList
	 */
	public List<NetMdDTO> getUpdateNetmdList() {
		return updateNetmdList;
	}
	/**
	 * @param updateNetmdList the updateNetmdList to set
	 */
	public void setUpdateNetmdList(List<NetMdDTO> updateNetmdList) {
		this.updateNetmdList = updateNetmdList;
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

}
