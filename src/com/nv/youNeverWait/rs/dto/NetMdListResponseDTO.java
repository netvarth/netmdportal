/**
 * NetMdListResponseDTO.java
 * 
 * @Author Asha Chandran
 *
 * Mar 12, 2013
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class NetMdListResponseDTO {
	private List<NetMdDetail> netMd = new ArrayList<NetMdDetail>();
	private Long count;
	private ErrorDTO error;
	private boolean success;

	/**
	 * @return the netMd
	 */
	public List<NetMdDetail> getNetMd() {
		return netMd;
	}

	/**
	 * @param netMd
	 *            the netMd to set
	 */
	public void setNetMd(List<NetMdDetail> netMd) {
		this.netMd = netMd;
	}

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

}
