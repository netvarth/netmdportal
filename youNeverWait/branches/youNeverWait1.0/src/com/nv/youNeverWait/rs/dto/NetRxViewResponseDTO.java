/**
 * NetRxViewResponseDTO.java
 *
 * @Author Luciya Jos
 * May 14, 2013 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author netvarth
 *
 */
public class NetRxViewResponseDTO {
	private NetRxDTO netRxDTO= new NetRxDTO();
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the netRxDTO
	 */
	public NetRxDTO getNetRxDTO() {
		return netRxDTO;
	}
	/**
	 * @param netRxDTO the netRxDTO to set
	 */
	public void setNetRxDTO(NetRxDTO netRxDTO) {
		this.netRxDTO = netRxDTO;
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
