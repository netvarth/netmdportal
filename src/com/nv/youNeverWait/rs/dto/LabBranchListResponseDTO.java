/**
 * LabBranchListResponseDTO.java
 * @author Asha Chandran
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;


public class LabBranchListResponseDTO {
	private List<LabBranchDTO> newBranchList = new ArrayList<LabBranchDTO>();
	private List<LabBranchDTO> updatedBranchList = new ArrayList<LabBranchDTO>();
	private List<LabBranchDTO> ownBranchList = new ArrayList<LabBranchDTO>();
	private String lastSynctime;
	private ErrorDTO error;
	private boolean success;

	
	/**
	 * @return the newBranchList
	 */
	public List<LabBranchDTO> getNewBranchList() {
		return newBranchList;
	}
	/**
	 * @param newBranchList the newBranchList to set
	 */
	public void setNewBranchList(List<LabBranchDTO> newBranchList) {
		this.newBranchList = newBranchList;
	}
	/**
	 * @return the updatedBranchList
	 */
	public List<LabBranchDTO> getUpdatedBranchList() {
		return updatedBranchList;
	}
	/**
	 * @param updatedBranchList the updatedBranchList to set
	 */
	public void setUpdatedBranchList(List<LabBranchDTO> updatedBranchList) {
		this.updatedBranchList = updatedBranchList;
	}
	/**
	 * @return the ownBranchList
	 */
	public List<LabBranchDTO> getOwnBranchList() {
		return ownBranchList;
	}
	/**
	 * @param ownBranchList the ownBranchList to set
	 */
	public void setOwnBranchList(List<LabBranchDTO> ownBranchList) {
		this.ownBranchList = ownBranchList;
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
	
	
}
