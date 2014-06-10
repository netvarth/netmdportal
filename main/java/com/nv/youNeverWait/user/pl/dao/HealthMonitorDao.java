/**
 * HealthMonitorDao.java
 * @author netvarth
 *
 * Version 1.0 Oct 16, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.pl.dao;


import com.nv.youNeverWait.rs.dto.SystemHealthDetail;
import com.nv.youNeverWait.rs.dto.SystemHealthResponse;

/**
 *
 *
 * @author Luciya Jose
 */
public interface HealthMonitorDao {

	/**
	 * @param systemHealth
	 * @return
	 */
	SystemHealthResponse checkSystemHealthInCritical(SystemHealthDetail systemHealth);

	/**
	 * @param systemHealth
	 * @return
	 */
	SystemHealthResponse checkSystemHealthForLab(SystemHealthDetail systemHealth);

	/**
	 * @param systemHealth
	 * @return
	 */
	SystemHealthResponse checkSystemHealthForNetmd(SystemHealthDetail systemHealth);
}
