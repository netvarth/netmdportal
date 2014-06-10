/**
 * ConfigServiceImpl.java
 * @author netvarth
 *
 * Version 1.0 Aug 30, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.impl;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.DeployConfigDataResponseDTO;
import com.nv.youNeverWait.user.bl.service.ConfigService;
import com.nv.youNeverWait.user.pl.dao.ConfigDao;

/**
 *
 *
 * @author Luciya Jose
 */
public class ConfigManager implements ConfigService {
	private ConfigDao configDao;

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.ConfigManager#configDataUpload(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public DeployConfigDataResponseDTO configDataUpload(
			MultipartFile multipartFile) {
		DeployConfigDataResponseDTO response = new DeployConfigDataResponseDTO();
		try {
			String s = new String(multipartFile.getBytes());
			response = configDao.parse(s);
		} catch (IOException e) {				
			ServiceException se =new ServiceException(ErrorCodeEnum.DatabaseError);			
			se.setDisplayErrMsg(true);
			throw se;	
		}			
		return response;
	}

	/**
	 * @return the configDao
	 */
	public ConfigDao getConfigDao() {
		return configDao;
	}

	/**
	 * @param configDao the configDao to set
	 */
	public void setConfigDao(ConfigDao configDao) {
		this.configDao = configDao;
	}

}
