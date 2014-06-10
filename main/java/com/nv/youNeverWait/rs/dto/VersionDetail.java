/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Joshi
 *
 */
public class VersionDetail {
	private HeaderDTO header;
	private String appName;
	private String sourceType;
	private String versionNumber;
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	public HeaderDTO getHeader() {
		return header;
	}
	public void setHeader(HeaderDTO header) {
		this.header = header;
	}
	
	
}
