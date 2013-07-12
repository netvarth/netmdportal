/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Luciya Jose
 *
 */
public class LabBranchResponseDTO {
	private ErrorDTO error;
	private boolean success;
	private LabBranchDTO branch;
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
	public LabBranchDTO getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(LabBranchDTO branch) {
		this.branch = branch;
	}
	
	
}
