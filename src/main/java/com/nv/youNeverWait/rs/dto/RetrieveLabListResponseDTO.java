/**
 * RetrieveLabListResponseDTO.java
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author netvarth
 *
 */
public class RetrieveLabListResponseDTO {
	private List<LabDTO> newLabList = new ArrayList<LabDTO>();
	private List<LabDTO> updatedLabList = new ArrayList<LabDTO>();
	private LabDTO ownLab = new LabDTO();
	private String lastSynctime;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the newLabList
	 */
	public List<LabDTO> getNewLabList() {
		return newLabList;
	}
	/**
	 * @param newLabList the newLabList to set
	 */
	public void setNewLabList(List<LabDTO> newLabList) {
		this.newLabList = newLabList;
	}
	/**
	 * @return the updatedLabList
	 */
	public List<LabDTO> getUpdatedLabList() {
		return updatedLabList;
	}
	/**
	 * @param updatedLabList the updatedLabList to set
	 */
	public void setUpdatedLabList(List<LabDTO> updatedLabList) {
		this.updatedLabList = updatedLabList;
	}
	
	/**
	 * @return the ownLab
	 */
	public LabDTO getOwnLab() {
		return ownLab;
	}
	/**
	 * @param ownLab the ownLab to set
	 */
	public void setOwnLab(LabDTO ownLab) {
		this.ownLab = ownLab;
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
