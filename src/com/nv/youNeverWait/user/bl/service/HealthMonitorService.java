/**
 * HealthMonitorManager.java
 * @author netvarth
 *
 * Version 1.0 Oct 16, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.service;


import com.nv.youNeverWait.rs.dto.HealthMonitorResponse;
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;
import com.nv.youNeverWait.rs.dto.SystemHealthResponse;

/**
 *
 *
 * @author Luciya Jose
 */
public interface HealthMonitorService {

	/**
	 * @param detail
	 * @return
	 */
	SystemHealthResponse checkSystemHealth(SystemHealthDetails systemHealthDetails);

}
