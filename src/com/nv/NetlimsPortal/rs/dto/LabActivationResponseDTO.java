/**
 * LabActivationResponseDTO.java
 */
package com.nv.NetlimsPortal.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class LabActivationResponseDTO {
	private ErrorDTO error;
	private boolean success;
	private List<LabBranchDTO> branchList= new ArrayList<LabBranchDTO>();
	private List<UserInfoDetail> users= new ArrayList<UserInfoDetail>();
	private LabDTO lab;
	
	/**
	 * @return the users
	 */
	public List<UserInfoDetail> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(List<UserInfoDetail> users) {
		this.users = users;
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
	 * @return the branchList
	 */
	public List<LabBranchDTO> getBranchList() {
		return branchList;
	}
	/**
	 * @param branchList the branchList to set
	 */
	public void setBranchList(List<LabBranchDTO> branchList) {
		this.branchList = branchList;
	}
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
	

}
