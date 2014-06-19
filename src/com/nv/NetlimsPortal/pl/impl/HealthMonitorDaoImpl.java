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
package com.nv.NetlimsPortal.pl.impl;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.nv.NetlimsPortal.common.pl.impl.GenericDaoHibernateImpl;
import com.nv.NetlimsPortal.exception.ServiceException;
import com.nv.NetlimsPortal.general.Constants;
import com.nv.NetlimsPortal.pl.dao.HealthMonitorDao;
import com.nv.NetlimsPortal.pl.entity.ErrorCodeEnum;
import com.nv.NetlimsPortal.pl.entity.LabBranchSystemInfoTbl;
import com.nv.NetlimsPortal.pl.entity.LabBranchTbl;
import com.nv.NetlimsPortal.pl.entity.LabHealthMonitorTbl;
import com.nv.NetlimsPortal.pl.query.Query;
import com.nv.NetlimsPortal.rs.dto.HealthMonitorResponse;
import com.nv.NetlimsPortal.rs.dto.Parameter;
import com.nv.NetlimsPortal.rs.dto.SystemHealthDetail;
import com.nv.NetlimsPortal.rs.dto.SystemHealthResponse;


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




private LabBranchSystemInfoTbl getSystemDetailsByBranchId(int branchId) {
	javax.persistence.Query query = em
			.createQuery(Query.GET_SYSTEM_DETAILS_BY_BRANCH_ID);
	query.setParameter("param1", branchId);
	return executeUniqueQuery(LabBranchSystemInfoTbl.class, query);
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
