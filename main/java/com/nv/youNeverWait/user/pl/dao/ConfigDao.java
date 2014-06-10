/**
 * ConfigDao.java
 * @author netvarth
 *
 * Version 1.0 Aug 30, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.pl.dao;

import com.nv.youNeverWait.rs.dto.DeployConfigDataResponseDTO;

/**
 *
 *
 * @author Luciya Jose
 */
public interface ConfigDao {

	/**
	 * @param s
	 * @return
	 */
	DeployConfigDataResponseDTO parse(String s);

}
