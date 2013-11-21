/**
 * LogDao.java
 * 
 * @Author Asha Chandran
 *
 * April 16, 2013
 */
package com.nv.youNeverWait.user.pl.dao;

import java.util.Date;


public interface LogDao {

	public void saveUserDetails(String ipAddress, String userName,
			String userType, Date loginTime, Date logoutTime,
			String applicationName, String actionName);

	/**
	 * @param ipAddress
	 * @param applicationName
	 * @param lastSyncTime
	 * @param branchId
	 * @param headOfficeId
	 */
	public void saveSyncDetails(String ipAddress, String applicationName,
			String lastSyncTime, int branchId, int headOfficeId);
}
