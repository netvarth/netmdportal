/**
 * ConfigResource.java
 * @author netvarth
 *
 * Version 1.0 Aug 30, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.DeployConfigDataResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.user.bl.service.ConfigManager;
import com.nv.youNeverWait.user.bl.validation.ConfigDataValidator;

/**
 *
 *
 * @author Luciya Jose
 */
@Controller
@RequestMapping("ui/config/")
public class ConfigResource {
	
	private ConfigDataValidator configDataValidator;
	private ConfigManager configManager;;
	
	/**
	 * for uploading config data
	 * @param request
	 * @return DeployConfigDataResponseDTO
	 */
	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public DeployConfigDataResponseDTO deployConfigData(HttpServletRequest request){
		DeployConfigDataResponseDTO response = new DeployConfigDataResponseDTO();
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile("file");			
			
			if(multipartFile==null){
				ErrorDTO error = configDataValidator.validateFile();			
				response.setError(error);
				response.setSuccess(false);
			}
			else
				response = configManager.configDataUpload(multipartFile);	
		}
		catch(ServiceException e){
			List<Parameter> parameters =e.getParamList();
			ErrorDTO error=new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * @return the configDataValidator
	 */
	public ConfigDataValidator getConfigDataValidator() {
		return configDataValidator;
	}

	/**
	 * @param configDataValidator the configDataValidator to set
	 */
	public void setConfigDataValidator(ConfigDataValidator configDataValidator) {
		this.configDataValidator = configDataValidator;
	}

	/**
	 * @return the configManager
	 */
	public ConfigManager getConfigManager() {
		return configManager;
	}

	/**
	 * @param configManager the configManager to set
	 */
	public void setConfigManager(ConfigManager configManager) {
		this.configManager = configManager;
	}

}
