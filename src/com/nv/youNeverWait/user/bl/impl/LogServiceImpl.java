/**
 * LogServiceImpl.java
 * 
 * @Author Asha Chandran
 *
 * April 16, 2013
 */
package com.nv.youNeverWait.user.bl.impl;

import java.util.Date;

import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.pl.dao.LogDao;

public class LogServiceImpl implements LogService {
	private LogDao logDao;

	/**
	 * @return the logDao
	 */
	public LogDao getLogDao() {
		return logDao;
	}

	/**
	 * @param logDao
	 *            the logDao to set
	 */
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	/**
	 * Method to save logged in user details to logtbl
	 */
	@Override
	public void saveUserDetails(String ipAddress, String userName,
			String userType, Date loginTime, Date logoutTime,
			String applicationName, String actionName) {
		logDao.saveUserDetails(ipAddress, userName, userType, loginTime,
				logoutTime, applicationName, actionName);

	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.LogService#saveSyncDetails(java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public void saveSyncDetails(String ipAddress, String applicationName,
			String lastSyncTime, int branchId, int headOfficeId) {
		logDao.saveSyncDetails(ipAddress, applicationName, lastSyncTime,
				branchId, headOfficeId);
	}


}