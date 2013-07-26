/**
 * ResultListResponseDTO.java
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nv
 *
 */
public class ResultListResponseDTO {
	
	private List<ResultDTO> resultList = new ArrayList<ResultDTO>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
	public List<ResultDTO> getResultList() {
		return resultList;
	}
	public void setResultList(List<ResultDTO> resultList) {
		this.resultList = resultList;
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
