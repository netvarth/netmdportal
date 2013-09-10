/**
 * SpecimenResource.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.ui;

import java.util.List;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveSpecimenResponseDTO;
import com.nv.youNeverWait.rs.dto.SpecimenDTO;
import com.nv.youNeverWait.rs.dto.SpecimenResponseDTO;
import com.nv.youNeverWait.user.bl.service.SpecimenManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.nv.youNeverWait.rs.dto.Parameter;
/**
 *
 *
 * @author Luciya Jose
 */
@Controller
@RequestMapping("ui/specimen/")
public class SpecimenResource {
	private SpecimenManager specimenManager;

	@RequestMapping(value = "createSpecimen", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createSpecimen(@RequestBody SpecimenDTO specimen){
		ResponseDTO response = new ResponseDTO();
		try{
			response = specimenManager.createSpecimen(specimen);
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

	@RequestMapping(value = "updateSpecimen", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateSpecimen(@RequestBody SpecimenDTO specimen){
		ResponseDTO response = new ResponseDTO();
		try{
			response = specimenManager.updateSpecimen(specimen);
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
	
	@RequestMapping(value = "deleteSpecimen/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteSpecimen(@PathVariable int uid){
		ResponseDTO response = new ResponseDTO();
		try{
			response = specimenManager.deleteSpecimen(uid);
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
	
	@RequestMapping(value = "viewSpecimen/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public SpecimenResponseDTO viewSpecimen(@PathVariable int uid){
		SpecimenResponseDTO response = new SpecimenResponseDTO();
		try{
			response = specimenManager.viewSpecimen(uid);
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
		
	@RequestMapping(value = "specimenList", method = RequestMethod.GET)
	@ResponseBody
	public RetrieveSpecimenResponseDTO specimenList(){
		RetrieveSpecimenResponseDTO response = new RetrieveSpecimenResponseDTO();
		try{
			response = specimenManager.specimenList();
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
	 * @return the specimenManager
	 */
	public SpecimenManager getSpecimenManager() {
		return specimenManager;
	}

	/**
	 * @param specimenManager the specimenManager to set
	 */
	public void setSpecimenManager(SpecimenManager specimenManager) {
		this.specimenManager = specimenManager;
	}
	

}
