/**
 * NetRxUserDTO.java
 *
 * @author Asha Chandran
 *
 * Mar 13, 2013
 */
package com.nv.youNeverWait.rs.dto;

public class NetRxUserDTO {
	private NetRxUserDetail user;
	private NetRxHeaderDTO header;
	private boolean success;
	private ErrorDTO error;

	

	public NetRxUserDetail getUser() {
		return user;
	}

	public void setUser(NetRxUserDetail user) {
		this.user = user;
	}

	

	/**
	 * @return the header
	 */
	public NetRxHeaderDTO getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(NetRxHeaderDTO header) {
		this.header = header;
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

}
