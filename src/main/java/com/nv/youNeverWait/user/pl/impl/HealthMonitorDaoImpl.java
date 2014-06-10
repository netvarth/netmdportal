/**
 * HealthMonitorDaoImpl.java
 * @author netvarth
 *
 * Version 1.0 Oct 16, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.pl.impl;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.LabBranchSystemInfoTbl;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.LabHealthMonitorTbl;
import com.nv.youNeverWait.pl.entity.NetmdBranchSystemInfoTbl;
import com.nv.youNeverWait.pl.entity.NetmdHealthMonitorTbl;
import com.nv.youNeverWait.pl.entity.NetmdPassphraseTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.HealthMonitorResponse;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.SystemHealthDetail;
import com.nv.youNeverWait.rs.dto.SystemHealthResponse;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.HealthMonitorDao;

/**
 *
 *
 * @author Luciya Jose
 */
public class HealthMonitorDaoImpl extends GenericDaoHibernateImpl implements HealthMonitorDao {

	@PersistenceContext()
	private EntityManager em;
	
/* (non-Javadoc)
 * @see com.nv.youNeverWait.user.pl.dao.HealthMonitorDao#checkSystemHealthForLab(com.nv.youNeverWait.rs.dto.SystemHealthDetail)
 */
@Override
@Transactional
public SystemHealthResponse checkSystemHealthForLab(
		SystemHealthDetail systemHealth) {
	SystemHealthResponse response= new SystemHealthResponse();
	
	LabBranchTbl labBranch = getById(LabBranchTbl.class,systemHealth.getBranchId());
	if (labBranch == null) {
		ServiceException se = new ServiceException(
				ErrorCodeEnum.InvalidBranchId);
		se.setDisplayErrMsg(true);
		throw se;
	}
	/* Getting the branch default system details from branch system info tbl */
	LabBranchSystemInfoTbl branchSystemInfo = getSystemDetailsByBranchId(labBranch
			.getId());
	if (branchSystemInfo == null) {
		ServiceException se = new ServiceException(
				ErrorCodeEnum.BranchSystemInfoNull);
		se.addParam(new Parameter(Constants.ID, Integer.toString(labBranch
				.getId())));
		se.setDisplayErrMsg(true);
		throw se;
	}
	systemHealth.setCriticalCpuLevel(branchSystemInfo.getCriticalCpuLevel());
	systemHealth.setCriticalHardDiskLevel(branchSystemInfo.getCriticalHardDiskSpaceLevel());
	systemHealth.setCriticalMemoryLevel(branchSystemInfo.getCriticalMemoryLevel());
	systemHealth.setFreqType(branchSystemInfo.getFreqType());
	systemHealth.setIntervalTime(branchSystemInfo.getIntervalTime());
	
	/**Checking system health in critical**/
	response=checkSystemHealthInCritical(systemHealth);
	
	/**Saving current system health details**/
	LabHealthMonitorTbl healthMonitor = new LabHealthMonitorTbl();
	Date currentDate= new Date();
	healthMonitor.setLabBranchTbl(labBranch);
	healthMonitor.setFreeHardDiskSpace(systemHealth.getFreeHardDiskSpaceInPercent());
	healthMonitor.setFreeCpuSpace(systemHealth.getFreeCpuSpaceInPercent());
	healthMonitor.setFreeMemorySpace(systemHealth.getFreeMemorySpaceInPercent());
	healthMonitor.setCreatedDateTime(currentDate);
	healthMonitor.setUpdatedDateTime(currentDate);
	healthMonitor.setTotalCpu(systemHealth.getTotalCpuUsage());
	healthMonitor.setTotalHardDisk(systemHealth.getTotalHardDiskUsed());
	healthMonitor.setTotalMemory(systemHealth.getTotalMemoryUsed());
	save(healthMonitor);
	return response;
}

/* (non-Javadoc)
 * @see com.nv.youNeverWait.user.pl.dao.HealthMonitorDao#checkSystemHealthForNetmd(com.nv.youNeverWait.rs.dto.SystemHealthDetail)
 */
@Override
@Transactional
public SystemHealthResponse checkSystemHealthForNetmd(
		SystemHealthDetail systemHealth) {
	SystemHealthResponse response= new SystemHealthResponse();
	
	NetmdPassphraseTbl netmdPassphraseBranch = getByPassphrase(systemHealth.getPassPhrase());
	if (netmdPassphraseBranch == null) {
		ServiceException se = new ServiceException(
				ErrorCodeEnum.InvalidPassphrase);
		se.setDisplayErrMsg(true);
		throw se;
	}
	/* Getting the branch default system details from netmd branch system info tbl */
	NetmdBranchSystemInfoTbl branchSystemInfo = getSystemDetailsByNetmdBranchId(netmdPassphraseBranch.getNetmdBranchTbl().getId(),netmdPassphraseBranch.getId());
	if (branchSystemInfo == null) {
		ServiceException se = new ServiceException(
				ErrorCodeEnum.NetmdBranchSystemInfoNull);
		se.setDisplayErrMsg(true);
		throw se;
	}
	systemHealth.setCriticalCpuLevel(branchSystemInfo.getCriticalCpuLevel());
	systemHealth.setCriticalHardDiskLevel(branchSystemInfo.getCriticalHardDiskLevel());
	systemHealth.setCriticalMemoryLevel(branchSystemInfo.getCriticalMemoryLevel());
	systemHealth.setFreqType(branchSystemInfo.getFreqType());
	systemHealth.setIntervalTime(branchSystemInfo.getIntervalTime());
	
	/**Checking system health in critical**/
	response=checkSystemHealthInCritical(systemHealth);
	
	/**Saving current system health details**/
	NetmdHealthMonitorTbl healthMonitor = new NetmdHealthMonitorTbl();
	Date currentDate= new Date();
	healthMonitor.setNetmdPassphraseTbl(netmdPassphraseBranch);
	healthMonitor.setFreeHardDiskSpace(systemHealth.getFreeHardDiskSpaceInPercent());
	healthMonitor.setFreeCpuSpace(systemHealth.getFreeCpuSpaceInPercent());
	healthMonitor.setFreeMemorySpace(systemHealth.getFreeMemorySpaceInPercent());
	healthMonitor.setCreatedDateTime(currentDate);
	healthMonitor.setUpdateDateTime(currentDate);
	healthMonitor.setTotalCpuSpace(systemHealth.getTotalCpuUsage());
	healthMonitor.setTotalHardDiskSpace(systemHealth.getTotalHardDiskUsed());
	healthMonitor.setTotalMemorySpace(systemHealth.getTotalMemoryUsed());
	save(healthMonitor);
	return response;
}

/* (non-Javadoc)
 * @see com.nv.youNeverWait.user.pl.dao.HealthMonitorDao#checkSystemHealth(com.nv.youNeverWait.rs.dto.SystemHealthDetail)
 */
@Override
@Transactional
public SystemHealthResponse checkSystemHealthInCritical(SystemHealthDetail systemHealth) {
	SystemHealthResponse response = new SystemHealthResponse();
	int intervalTym = 0;
	String frequencyType = null;
	boolean criticalFlag = false;
			
	/* Checking whether system is in critical condition */
	if (systemHealth.getFreeHardDiskSpaceInPercent() <= systemHealth.getCriticalHardDiskLevel()) {
		criticalFlag = true;
		intervalTym = systemHealth.getIntervalTime();
		frequencyType = systemHealth.getFreqType();
	}

	if (systemHealth.getFreeMemorySpaceInPercent() <=systemHealth.getCriticalMemoryLevel()) {
		criticalFlag = true;
		intervalTym = systemHealth.getIntervalTime();
		frequencyType = systemHealth.getFreqType();
	}

	if (systemHealth.getFreeCpuSpaceInPercent() <= systemHealth.getCriticalCpuLevel()) {
		criticalFlag = true;
		intervalTym = systemHealth.getIntervalTime();
		frequencyType = systemHealth.getFreqType();
	}
	HealthMonitorResponse systemHealthResponse = new HealthMonitorResponse();
	systemHealthResponse.setIntervalTime(Integer.toString(intervalTym));
	systemHealthResponse.setFreqPeriod(frequencyType);
	systemHealthResponse.setCritical(criticalFlag);
	response.setSystemHealth(systemHealthResponse);
	response.setFreeCpuSpaceInPercent(systemHealth.getFreeCpuSpaceInPercent());
	response.setFreeHardDiskSpaceInPercent(systemHealth.getFreeHardDiskSpaceInPercent());
	response.setFreeMemorySpaceInPercent(systemHealth.getFreeMemorySpaceInPercent());
	response.setSuccess(true);
	return response;
}


/**
 * @param netmdBranchId
 * @param netmdPassphraseId
 * @return
 */
private NetmdBranchSystemInfoTbl getSystemDetailsByNetmdBranchId(int netmdBranchId, int netmdPassphraseId) {
	javax.persistence.Query query = em
			.createQuery(Query.GET_NETMD_BRANCH_SYSTEM_DETAILS);
	query.setParameter("param1", netmdBranchId);
	query.setParameter("param2", netmdPassphraseId);
	return executeUniqueQuery(NetmdBranchSystemInfoTbl.class, query);
}

private LabBranchSystemInfoTbl getSystemDetailsByBranchId(int branchId) {
	javax.persistence.Query query = em
			.createQuery(Query.GET_SYSTEM_DETAILS_BY_BRANCH_ID);
	query.setParameter("param1", branchId);
	return executeUniqueQuery(LabBranchSystemInfoTbl.class, query);
}


/**
 * @param passPhrase
 * @return
 */
private NetmdPassphraseTbl getByPassphrase(String passPhrase) {
	javax.persistence.Query query = em
			.createQuery(Query.GET_NETMD_BRANCH_PASSPHRASE);
	query.setParameter("param1", passPhrase);
	return executeUniqueQuery(NetmdPassphraseTbl.class, query);
}

/**
 * @return the em
 */
public EntityManager getEm() {
	return em;
}

/**
 * @param em the em to set
 */
public void setEm(EntityManager em) {
	this.em = em;
}

}
