/**
 * NetMdResource.java
 * 
 * @Author Asha Chandran
 *
 * Feb 3, 2013
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class LabListResponseDTO {
	private List<LabDetail> lab = new ArrayList<LabDetail>();
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
	 * @return the lab
	 */
	public List<LabDetail> getLab() {
		return lab;
	}

	/**
	 * @param lab
	 *            the lab to set
	 */
	public void setLab(List<LabDetail> lab) {
		this.lab = lab;
	}
}
