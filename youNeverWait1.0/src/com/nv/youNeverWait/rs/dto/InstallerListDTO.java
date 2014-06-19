package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class InstallerListDTO {
	
	private List<InstallerDTO> installers = new ArrayList<InstallerDTO>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
	
	/**
	 * @param installers
	 * @param count
	 * @param error
	 * @param success
	 */
	public InstallerListDTO(List<InstallerDTO> installers, Long count,
			ErrorDTO error, boolean success) {
		super();
		this.installers = installers;
		this.count = count;
		this.error = error;
		this.success = success;
	}
	
	/**
	 * @return the installers
	 */
	public List<InstallerDTO> getInstallers() {
		return installers;
	}
	/**
	 * 
	 */
	public InstallerListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param installers the installers to set
	 */
	public void setInstallers(List<InstallerDTO> installers) {
		this.installers = installers;
	}
	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
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
