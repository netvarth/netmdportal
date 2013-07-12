/**
 * RetrieveNetmdBranchListResponseDTO
 * 
 * Mar 11,2013
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luciya
 *
 */
public class RetrieveNetmdBranchListResponseDTO {
	private	List<NetMdBranchDTO> newNetmdBranchList = new ArrayList<NetMdBranchDTO>();
	private List<NetMdBranchDTO> updateNetmdBranchList = new ArrayList<NetMdBranchDTO>();
	private String lastSyncTime;
	private boolean success;
	private ErrorDTO error;
	
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
	/**
	 * @return the newNetmdBranchList
	 */
	public List<NetMdBranchDTO> getNewNetmdBranchList() {
		return newNetmdBranchList;
	}
	/**
	 * @param newNetmdBranchList the newNetmdBranchList to set
	 */
	public void setNewNetmdBranchList(List<NetMdBranchDTO> newNetmdBranchList) {
		this.newNetmdBranchList = newNetmdBranchList;
	}
	/**
	 * @return the updateNetmdBranchList
	 */
	public List<NetMdBranchDTO> getUpdateNetmdBranchList() {
		return updateNetmdBranchList;
	}
	/**
	 * @param updateNetmdBranchList the updateNetmdBranchList to set
	 */
	public void setUpdateNetmdBranchList(List<NetMdBranchDTO> updateNetmdBranchList) {
		this.updateNetmdBranchList = updateNetmdBranchList;
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
