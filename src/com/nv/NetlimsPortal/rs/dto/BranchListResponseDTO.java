/**
 * BranchListResponseDTO.java
 */
package com.nv.NetlimsPortal.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luciya Jose
 *
 */
public class BranchListResponseDTO {
	private List<BranchDetail> branch = new ArrayList<BranchDetail>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the branch
	 */
	public List<BranchDetail> getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(List<BranchDetail> branch) {
		this.branch = branch;
	}
	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
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
