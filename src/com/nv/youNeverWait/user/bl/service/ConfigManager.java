/**
 * ConfigManager.java
 * @author netvarth
 *
 * Version 1.0 Aug 30, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.service;

import org.springframework.web.multipart.MultipartFile;

import com.nv.youNeverWait.rs.dto.DeployConfigDataResponseDTO;

/**
 *
 *
 * @author Luciya Jose
 */
public interface ConfigManager {

	/**
	 * @param multipartFile
	 * @return
	 */
	DeployConfigDataResponseDTO configDataUpload(MultipartFile multipartFile);

}
