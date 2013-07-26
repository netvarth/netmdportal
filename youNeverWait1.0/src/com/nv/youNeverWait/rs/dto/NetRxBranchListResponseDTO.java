/**
 * NetRxBranchListResponseDTO.java
 *
 * @Author Luciya Jos
 * May 13, 2013 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luciya
 *
 */
public class NetRxBranchListResponseDTO {
	private List<NetRxBranchDetail> netRxBranch = new ArrayList<NetRxBranchDetail>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
	
	/**
	 * @return the netRxBranch
	 */
	public List<NetRxBranchDetail> getNetRxBranch() {
		return netRxBranch;
	}
	/**
	 * @param netRxBranch the netRxBranch to set
	 */
	public void setNetRxBranch(List<NetRxBranchDetail> netRxBranch) {
		this.netRxBranch = netRxBranch;
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
