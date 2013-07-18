/**
 * NetMdListResponseDTO.java
 * 
 * @author Asha Chandran
 *
 * Mar 11, 2013
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class NetMdBranchListResponseDTO {
	private List<NetMdBranchDetail> netmdBranch = new ArrayList<NetMdBranchDetail>();
	private Long count;
	private ErrorDTO error;
	private boolean success;

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
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
	 * @param error
	 *            the error to set
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
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the netmdBranch
	 */
	public List<NetMdBranchDetail> getNetmdBranch() {
		return netmdBranch;
	}

	/**
	 * @param netmdBranch the netmdBranch to set
	 */
	public void setNetmdBranch(List<NetMdBranchDetail> netmdBranch) {
		this.netmdBranch = netmdBranch;
	}

}
