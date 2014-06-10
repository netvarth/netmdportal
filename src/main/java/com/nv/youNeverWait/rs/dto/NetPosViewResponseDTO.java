/**
 * NetPosViewresponseDTO.java
 * Jithinraj
 * Nov 26, 2013
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Mani
 *
 */
public class NetPosViewResponseDTO {
	private NetPosDTO netPos ;
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
	public NetPosDTO getNetPos() {
		return netPos;
	}
	/**
	 * @param netMd the netMd to set
	 */
	public void setNetPos(NetPosDTO netPos) {
		this.netPos = netPos;
	}
}



