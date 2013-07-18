/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author netvarth
 *
 */
public class RetrieveUserListResponseDTO {
	private List<UserInfoDetail> newUserList = new ArrayList<UserInfoDetail>();
	private List<UserInfoDetail> updatedUserList = new ArrayList<UserInfoDetail>();
	private String lastSynctime;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the newUserList
	 */
	public List<UserInfoDetail> getNewUserList() {
		return newUserList;
	}
	/**
	 * @param newUserList the newUserList to set
	 */
	public void setNewUserList(List<UserInfoDetail> newUserList) {
		this.newUserList = newUserList;
	}
	/**
	 * @return the updatedUserList
	 */
	public List<UserInfoDetail> getUpdatedUserList() {
		return updatedUserList;
	}
	/**
	 * @param updatedUserList the updatedUserList to set
	 */
	public void setUpdatedUserList(List<UserInfoDetail> updatedUserList) {
		this.updatedUserList = updatedUserList;
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
	
	/**
	 * @param newUserList
	 * @param updatedUserList
	 * @param lastSynctime
	 * @param error
	 * @param success
	 */
	public RetrieveUserListResponseDTO(List<UserInfoDetail> newUserList,
			List<UserInfoDetail> updatedUserList, String lastSynctime,
			ErrorDTO error, boolean success) {
		super();
		this.newUserList = newUserList;
		this.updatedUserList = updatedUserList;
		this.lastSynctime = lastSynctime;
		this.error = error;
		this.success = success;
	}
	/**
	 * 
	 */
	public RetrieveUserListResponseDTO() {
		
	}
	

}
