/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Joshi
 *
 */
public class VersionAvailabilityResponse {
	private String availableVersion;
	private String displayMsg;
	private boolean success;
	private ErrorDTO error;
	public String getAvailableVersion() {
		return availableVersion;
	}
	public void setAvailableVersion(String availableVersion) {
		this.availableVersion = availableVersion;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	public String getDisplayMsg() {
		return displayMsg;
	}
	public void setDisplayMsg(String displayMsg) {
		this.displayMsg = displayMsg;
	}
	
}
