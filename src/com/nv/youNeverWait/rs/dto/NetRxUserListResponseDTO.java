/**
 * NetRxUserListResponseDTO.java
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class NetRxUserListResponseDTO {
	private List<NetRxUserDetail> netRxUser = new ArrayList<NetRxUserDetail>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
	public List<NetRxUserDetail> getNetRxUser() {
		return netRxUser;
	}
	public void setNetRxUser(List<NetRxUserDetail> netRxUser) {
		this.netRxUser = netRxUser;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
