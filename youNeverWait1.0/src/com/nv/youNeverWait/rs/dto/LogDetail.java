/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.pl.entity.LogTbl;

/**
 * @author Luciya Jose
 * 
 */
public class LogDetail {
	private int id;
	private String userName;
	private String actionName;
	private String applicationName;
	private String userType;
	private String loginTime;
	private String logoutTime;
	private String ipAddress;
	private String actionTime;
	private String actionDate;


	public LogDetail(LogTbl logTbl,String loginTime,String logoutTime,String actionDate) {
		this.setActionName(logTbl.getActionName());
		this.setApplicationName(logTbl.getApplicationName());
		this.setId(logTbl.getId());
		this.setIpAddress(logTbl.getIpAddress());
		this.setLoginTime(loginTime);
		this.setLogoutTime(logoutTime);
		this.setUserName(logTbl.getUserName());
		this.setUserType(logTbl.getUserType() );
		this.setActionDate(actionDate);
		this.setActionTime(logTbl.getActionTime());
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName
	 *            the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * @param applicationName
	 *            the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the loginTime
	 */
	public String getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime
	 *            the loginTime to set
	 */
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the logoutTime
	 */
	public String getLogoutTime() {
		return logoutTime;
	}

	/**
	 * @param logoutTime
	 *            the logoutTime to set
	 */
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the actionTime
	 */
	public String getActionTime() {
		return actionTime;
	}

	/**
	 * @param actionTime the actionTime to set
	 */
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}

	/**
	 * @return the actionDate
	 */
	public String getActionDate() {
		return actionDate;
	}

	/**
	 * @param actionDate the actionDate to set
	 */
	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

}
