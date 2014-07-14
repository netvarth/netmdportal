/**
 * ResultResource.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.portal.rs.netlims;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.user.bl.service.ResultService;


/**
 * 
 */
@Controller
@RequestMapping("ui/result/")
public class ResultResource {
	private ResultService resultService;


	
	/**
	 * list of patient result result
	 * @param patientId 
	 * @return ResultListResponseDTO
	 */
	@RequestMapping(value = "list/{patientId}", method = RequestMethod.GET)
	@ResponseBody
	public ResultListResponseDTO listResult(@PathVariable String patientId) {
		ResultListResponseDTO response = new ResultListResponseDTO();
		try {
			response = resultService.listResult(patientId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}



	/**
	 * @return the resultService
	 */
	public ResultService getResultService() {
		return resultService;
	}



	/**
	 * @param resultService the resultService to set
	 */
	public void setResultService(ResultService resultService) {
		this.resultService = resultService;
	}
}
