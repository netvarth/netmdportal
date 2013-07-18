/**
 * NetMdActivationResponseDTO.java
 *
 * Mar 08, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class NetMdActivationResponseDTO {
	
	private ErrorDTO error;
	private boolean success;
	private NetMdBranchDTO branch;
	private NetMdDTO netmd;
	private List<NetMdUserDetail> user = new ArrayList<NetMdUserDetail>();
	private boolean primary;
	
	/**
	 * @return the primary
	 */
	public boolean isPrimary() {
		return primary;
	}
	/**
	 * @param primary the primary to set
	 */
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	/**
	 * @return the netmd
	 */
	public NetMdDTO getNetmd() {
		return netmd;
	}
	/**
	 * @param netmd the netmd to set
	 */
	public void setNetmd(NetMdDTO netmd) {
		this.netmd = netmd;
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
	 * @return the branch
	 */
	public NetMdBranchDTO getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(NetMdBranchDTO branch) {
		this.branch = branch;
	}
	/**
	 * @return the user
	 */
	public List<NetMdUserDetail> getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(List<NetMdUserDetail> user) {
		this.user = user;
	}

	
}
