/**
 * UserLogListResponseDTO.java
 * 
 * @Author Asha Chandran
 *
 * Apr 29, 2013
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class UserLogListResponseDTO {
	private List<LogDetail> log = new ArrayList<LogDetail>();
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
	 * @return the log
	 */
	public List<LogDetail> getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(List<LogDetail> log) {
		this.log = log;
	}

}
