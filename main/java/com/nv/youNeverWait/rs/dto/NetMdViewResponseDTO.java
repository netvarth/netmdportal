/**
 * LabResponseDTO.java
 *
 * Jan 16, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;



/**
 * 
 */
public class NetMdViewResponseDTO {

	private NetMdDTO netMd ;
	private ErrorDTO error;
	private boolean success;
	
	
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
	/**
	 * @return the netMd
	 */
	public NetMdDTO getNetMd() {
		return netMd;
	}
	/**
	 * @param netMd the netMd to set
	 */
	public void setNetMd(NetMdDTO netMd) {
		this.netMd = netMd;
	}
}
