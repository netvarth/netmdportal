/**
 * NetPosListResponseDTO.java
 * Jithinraj
 * Dec 26, 2013
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mani
 *
 */
public class NetPosListResponseDTO {
	private List<NetPosDetail> netPos = new ArrayList<NetPosDetail>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
	
	
	
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
	public List<NetPosDetail> getNetPos() {
		return netPos;
	}
	public void setNetPos(List<NetPosDetail> netPos) {
		this.netPos = netPos;
	}


}
