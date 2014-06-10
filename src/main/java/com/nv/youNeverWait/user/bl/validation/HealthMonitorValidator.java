/**
 * HealthMonitorValidator.java
 * @author netvarth
 *
 * Version 1.0 Oct 16, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.validation;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;

/**
 *
 *
 * @author Luciya Jose
 */
public class HealthMonitorValidator {

	/**
	 * Validating system health monitoring details
	 * @param systemHealthDetails
	 */
	public void validateSystemHealthDetails(
			SystemHealthDetails systemHealthDetails) {
		if (systemHealthDetails.getHardDiskUsed() == null
				|| systemHealthDetails.getHardDiskUsed().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.HardDiskSizeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (systemHealthDetails.getMemoryUsed() == null
				||systemHealthDetails.getMemoryUsed().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.MemorySizeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (systemHealthDetails.getCpuUsage() == null
				||systemHealthDetails.getCpuUsage().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.CpuUsageNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		
		if (systemHealthDetails.getTotalHardDiskSpace()== null
				|| systemHealthDetails.getHardDiskUsed().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.HardDiskSpaceNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (systemHealthDetails.getTotalMemorySpace() == null
				||systemHealthDetails.getMemoryUsed().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.MemorySpaceNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		if (systemHealthDetails.getTotalCpuSpace() == null
				||systemHealthDetails.getMemoryUsed().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.CpuSpaceNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
	}

}
