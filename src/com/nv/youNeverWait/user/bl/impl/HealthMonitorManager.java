/**
 * HealthMonitorServiceImpl.java
 * @author netvarth
 *
 * Version 1.0 Oct 16, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.impl;

import com.nv.youNeverWait.rs.dto.HealthMonitorResponse;
import com.nv.youNeverWait.rs.dto.SystemHealthDetail;
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;
import com.nv.youNeverWait.rs.dto.SystemHealthResponse;
import com.nv.youNeverWait.user.bl.service.HealthMonitorService;
import com.nv.youNeverWait.user.bl.validation.HealthMonitorValidator;
import com.nv.youNeverWait.user.pl.dao.HealthMonitorDao;

/**
 * 
 * 
 * @author Luciya Jose
 */
public class HealthMonitorManager implements HealthMonitorService {

	private HealthMonitorValidator hMonitorValidator;
	/**
	 * @return the hMonitorDao
	 */
	public HealthMonitorDao gethMonitorDao() {
		return hMonitorDao;
	}

	/**
	 * @param hMonitorDao the hMonitorDao to set
	 */
	public void sethMonitorDao(HealthMonitorDao hMonitorDao) {
		this.hMonitorDao = hMonitorDao;
	}

	private HealthMonitorDao hMonitorDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.bl.service.HealthMonitorManager#checkSystemHealth
	 * (com.nv.youNeverWait.rs.dto.HealthMonitorDetail)
	 */
	@Override
	public SystemHealthResponse checkSystemHealth(
			SystemHealthDetails systemHealthDetails) {
		SystemHealthResponse response = new SystemHealthResponse();

		/* Validates health monitor details */
		hMonitorValidator.validateSystemHealthDetails(systemHealthDetails);

		/** System used details **/
		long cpuUsage = Long.parseLong(systemHealthDetails.getCpuUsage());
		long memoryUsed = Long.parseLong(systemHealthDetails.getMemoryUsed());
		long hardDiskUsed = Long.parseLong(systemHealthDetails
				.getHardDiskUsed());

		/** System total space **/
		long totalCpuUsage = Long.parseLong(systemHealthDetails
				.getTotalCpuSpace());
		long totalMemoryUsed = Long.parseLong(systemHealthDetails
				.getTotalMemorySpace());
		long totalHardDiskUsed = Long.parseLong(systemHealthDetails
				.getTotalHardDiskSpace());

		/** System free space details **/
		long freeHardDiskSpace = totalHardDiskUsed - hardDiskUsed;
		long freeMemorySpace = totalMemoryUsed - memoryUsed;
		long freeCpuSpace = totalCpuUsage - cpuUsage;

		/** System free space in percentage **/
		long freeHardDiskSpaceInPercent = freeHardDiskSpace * 100
				/ totalHardDiskUsed;
		long freeMemorySpaceInPercent = freeMemorySpace * 100 / totalMemoryUsed;
		long freeCpuSpaceInPercent = freeCpuSpace * 100 / totalCpuUsage;

		/** Setting detials for checking critical condition **/
		SystemHealthDetail systemHealth = new SystemHealthDetail();
		systemHealth.setBranchId(systemHealthDetails.getHeader().getBranchId());
		systemHealth.setPassPhrase(systemHealthDetails.getHeader().getPassPhrase());
		systemHealth.setTotalCpuUsage(totalCpuUsage);
		systemHealth.setTotalHardDiskUsed(totalHardDiskUsed);
		systemHealth.setTotalMemoryUsed(totalMemoryUsed);
		systemHealth.setFreeCpuSpaceInPercent(freeCpuSpaceInPercent);
		systemHealth.setFreeHardDiskSpaceInPercent(freeHardDiskSpaceInPercent);
		systemHealth.setFreeMemorySpaceInPercent(freeMemorySpaceInPercent);
		systemHealth.setAppType(systemHealthDetails.getAppType());
		
		/**If application is NetLims**/
		if (systemHealthDetails.getAppType().equals("netlims")) {
			response=hMonitorDao.checkSystemHealthForLab(systemHealth);
		}
		/**If application is NetMd**/
		if (systemHealthDetails.getAppType().equals("netmd")) {
			response=hMonitorDao.checkSystemHealthForNetmd(systemHealth);
		}
		return response;
	}

	/**
	 * @return the hMonitorValidator
	 */
	public HealthMonitorValidator gethMonitorValidator() {
		return hMonitorValidator;
	}

	/**
	 * @param hMonitorValidator
	 *            the hMonitorValidator to set
	 */
	public void sethMonitorValidator(HealthMonitorValidator hMonitorValidator) {
		this.hMonitorValidator = hMonitorValidator;
	}

}
