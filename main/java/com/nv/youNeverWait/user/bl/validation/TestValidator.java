/**
 * TestValidator.java
 * @author netvarth
 *
 * Version 1.0 Sep 2, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.validation;


import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.AddTestDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.queryBuilder.LabPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.TestPropertyEnum;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Luciya Jose
 */
public class TestValidator extends FilterValidator {

	/**
	 * @param filter
	 * @return
	 */
	public ErrorDTO validateTestFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = TestPropertyEnum.valueOf(exp.getName());
			} catch (IllegalArgumentException e) {
				error = getInvalidExpNameError(exp);
				return error;
			}
			error = validateExp(exp, property);
			if (error != null) {
				return error;
			}
		}
		return null;
	}

	/**
	 * @param testDTO
	 * @return
	 */
	public ErrorDTO validateTest(AddTestDTO testDTO) {
		ErrorDTO error = new ErrorDTO();
		if(testDTO.getName().equals("") && testDTO.getName()==null && testDTO.getName().matches("\\d*")){
			error.setErrCode(ErrorCodeEnum.InvalidTestName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

}
