/**
 * LogDaoImpl.java
 *
 * April 16, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.pl.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.LabTbl;
import com.nv.youNeverWait.pl.entity.LogTbl;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.entity.NetmdTbl;
import com.nv.youNeverWait.pl.entity.NetrxBranchTbl;
import com.nv.youNeverWait.pl.entity.NetrxTbl;
import com.nv.youNeverWait.pl.entity.SuperAdminTbl;
import com.nv.youNeverWait.pl.entity.SyncLogTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.user.pl.dao.LogDao;

public class LogDaoImpl extends GenericDaoHibernateImpl implements LogDao {

	@PersistenceContext()
	private EntityManager em;

	/**
	 * Save user details
	 */
	@Override
	@Transactional
	public void saveUserDetails(String ipAddress, String userName,
			String userType, Date loginTime, Date logoutTime,
			String applicationName, String actionName) {
		LogTbl log = new LogTbl();
		log.setActionName(actionName);
		log.setApplicationName(applicationName);
		log.setIpAddress(ipAddress);
		log.setLoginTime(loginTime);
		log.setLogoutTime(logoutTime);
		log.setUserName(userName);
		log.setUserType(userType);
		log.setActionDate(new Date());
		SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
		log.setActionTime(df.format(log.getActionDate()));
		try {
			ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			HttpServletRequest request = t.getRequest();
			if (request.getSession().getServletContext()
					.getAttribute("logEnabled") == null) {
				SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
				request.getSession().getServletContext()
						.setAttribute("logEnabled", superAdmin.getEnableLog());
			}
			System.out.println(request.getSession().getServletContext()
					.getAttribute("logEnabled"));
			if ((Boolean) request.getSession().getServletContext()
					.getAttribute("logEnabled")) {
				save(log);
			}
		} catch (ServiceException se) {

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.LogDao#saveSyncDetails(java.lang.String,
	 * java.lang.String, java.lang.String, int, int)
	 */
	@Override
	@Transactional
	public void saveSyncDetails(String ipAddress, String applicationName,
			String lastSyncTime, int branchId, int headOfficeId) {
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		String branchName = getBranchByApplication(branchId,applicationName);
		String headOfficeName = getHeadByApplication(headOfficeId,applicationName);
		SyncLogTbl syncLogTbl = new SyncLogTbl();
		syncLogTbl.setIpAddress(ipAddress);
		syncLogTbl.setApplicationName(applicationName);
		try {
			syncLogTbl.setLastSyncTime(df.parse(lastSyncTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		syncLogTbl.setBranchName(branchName);
		syncLogTbl.setHeadOfficeName(headOfficeName);

		try {
			ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			HttpServletRequest request = t.getRequest();
			if (request.getSession().getServletContext()
					.getAttribute("syncLogEnabled") == null) {
				SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
				request.getSession()
						.getServletContext()
						.setAttribute("syncLogEnabled",
								superAdmin.getEnableSyncLog());
			}
			System.out.println(request.getSession().getServletContext()
					.getAttribute("syncLogEnabled"));
			if ((Boolean) request.getSession().getServletContext()
					.getAttribute("syncLogEnabled")) {
				save(syncLogTbl);
			}
		} catch (ServiceException se) {

		}

	}

	/**
	 * @param headOfficeId
	 * @param applicationName 
	 * @return
	 */
	private String getHeadByApplication(int headOfficeId, String applicationName) {
		String headOfficeName=null;
		if(applicationName.equals(Constants.NETLIMS)){
			LabTbl lab= getById(LabTbl.class, headOfficeId);
			headOfficeName=lab.getName();
		}
		if(applicationName.equals(Constants.NETMD)){
			NetmdTbl netmd= getById(NetmdTbl.class, headOfficeId);
			headOfficeName=netmd.getName();
		}
		if(applicationName.equals(Constants.NETRX)){
			NetrxTbl netrx= getById(NetrxTbl.class, headOfficeId);
			headOfficeName=netrx.getName();
		}
		return headOfficeName;
	}

	/**
	 * @param branchId
	 * @param applicationName 
	 * @return
	 */
	private String getBranchByApplication(int branchId, String applicationName) {
		String branchName=null;
		if(applicationName.equals(Constants.NETLIMS)){
			LabBranchTbl labBranch= getById(LabBranchTbl.class, branchId);
			branchName=labBranch.getName();
		}
		if(applicationName.equals(Constants.NETMD)){
			NetmdBranchTbl netmdBranch= getById(NetmdBranchTbl.class, branchId);
			branchName=netmdBranch.getName();
		}
		if(applicationName.equals(Constants.NETRX)){
			NetrxBranchTbl netrxBranch= getById(NetrxBranchTbl.class, branchId);
			branchName=netrxBranch.getName();
		}
		return branchName;
	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

}