
/**
 * LabUserListResponseDTO.java
 * @author Asha Chandran
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class LabUserListResponseDTO {
	private List<LabUserDetail> netLimsUser = new ArrayList<LabUserDetail>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the netLimsUser
	 */
	public List<LabUserDetail> getNetLimsUser() {
		return netLimsUser;
	}
	/**
	 * @param netLimsUser the netLimsUser to set
	 */
	public void setNetLimsUser(List<LabUserDetail> netLimsUser) {
		this.netLimsUser = netLimsUser;
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
