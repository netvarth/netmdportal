/**
 * TestResource.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.ui;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.AddTestDTO;
import com.nv.youNeverWait.rs.dto.DeleteTestResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UpdateTestResponseDTO;
import com.nv.youNeverWait.rs.dto.ViewTestResponseDTO;
import com.nv.youNeverWait.user.bl.service.TestManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.List;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
/**
 *
 *
 * @author Luciya Jose
 */
@Controller
@RequestMapping("ui/test/")
public class TestResource {
	private TestManager testManager;

	/**
	 * for creating new test
	 * @param testDTO
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createTest", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createTest(@RequestBody AddTestDTO testDTO){
		ResponseDTO response=new ResponseDTO();
		try
		{
			response=testManager.createTest(testDTO);
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
	 * for updating a test
	 * @param testDTO
	 * @return UpdateTestResponseDTO
	 */
	@RequestMapping(value = "updateTest", method = RequestMethod.POST)
	@ResponseBody
	public UpdateTestResponseDTO updateTest(@RequestBody AddTestDTO testDTO){
		UpdateTestResponseDTO response = new UpdateTestResponseDTO();
		try{
			response = testManager.updateTest(testDTO);
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
	 * for deleting a test
	 * @param uid
	 * @return DeleteTestResponseDTO
	 */
	@RequestMapping(value = "deleteTest/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public DeleteTestResponseDTO deleteTest(@PathVariable String uid){		
		DeleteTestResponseDTO  response= new DeleteTestResponseDTO();
		try
		{
			response=testManager.deleteTest(uid);
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
	 * View details of a test
	 * @param uid
	 * @return ViewTestResponseDTO
	 */
	@RequestMapping(value = "viewTest/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public ViewTestResponseDTO  viewTest(@PathVariable String uid){		
		ViewTestResponseDTO  response=new ViewTestResponseDTO();
		try
		{
			response=testManager.viewTest(uid);
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
	 * @return the testManager
	 */
	public TestManager getTestManager() {
		return testManager;
	}

	/**
	 * @param testManager the testManager to set
	 */
	public void setTestManager(TestManager testManager) {
		this.testManager = testManager;
	}
	

}
