/**
 * LogService.java
 * 
 * @Author Asha Chandran
 *
 * April 16, 2013
 */
package com.nv.NetlimsPortal.bl.service;

import java.util.Date;

public interface LogService {
	public void saveUserDetails(String ipAddress, String userName,
			String userType, Date loginTime, Date logoutTime,
			String applicationName, String actionName);
	public void saveSyncDetails(String ipAddress,String applicationName, String lastSyncTime,
			int branchId,int headOfficeId);

}
