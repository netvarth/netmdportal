/**
 * ConfigValidator.java
 * @author netvarth
 *
 * Version 1.0 Aug 30, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.validation;

import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.Parameter;

import java.util.ArrayList;
import java.util.List;
/**
 *
 *
 * @author Luciya Jose
 */
public class ConfigDataValidator {

	/**
	 * @return
	 */
	public ErrorDTO validateFile() {
		ErrorDTO error=new ErrorDTO();
		error.setErrCode(ErrorCodeEnum.NoFileSelected.getErrCode());
		return error;
	}

	/**
	 * @param valueAsText
	 * @return
	 */
	public ErrorDTO validateSpecimenUid(String specimenUid) {
		ErrorDTO error=new ErrorDTO();
		if(specimenUid.equals("null")){
			List<Parameter> parameters = new ArrayList<Parameter>();
			Parameter parameter=new Parameter();
			parameter.setParameterName("uid");
			parameter.setValue(specimenUid);
			parameters.add(parameter);
			error.setErrCode(ErrorCodeEnum.SpecimenUidNull.getErrCode());
			error.setParams(parameters);
			}
			else 
				error=null;
			return error;
	}

	/**
	 * @param valueAsText
	 * @return
	 */
	public ErrorDTO validateTestUid(String testUid) {
		ErrorDTO error=new ErrorDTO();
		if(testUid.equals("null")){
			List<Parameter> parameters = new ArrayList<Parameter>();
			Parameter parameter=new Parameter();
			parameter.setParameterName("uid");
			parameter.setValue(testUid);
			parameters.add(parameter);
			error.setErrCode(ErrorCodeEnum.TestCodeNull.getErrCode());
			error.setParams(parameters);
			}
			else 
				error=null;
			return error;
	}


}
