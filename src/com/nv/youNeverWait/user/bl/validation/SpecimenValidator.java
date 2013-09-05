/**
 * SpecimenValidator.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.validation;

import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.SpecimenDTO;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.queryBuilder.SpecimenPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.TestPropertyEnum;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;

/**
 *
 *
 * @author Luciya Jose
 */
public class SpecimenValidator  extends FilterValidator{

	/**
	 * @param filterDTO
	 * @return
	 */
	public ErrorDTO validateSpecimenFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = SpecimenPropertyEnum.valueOf(exp.getName());
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
	 * @param specimen
	 * @return
	 */
	public ErrorDTO validateSpecimen(SpecimenDTO specimen) {
		ErrorDTO error = new ErrorDTO();
		if(specimen.getSpecimenName().equals("") && specimen.getSpecimenName()==null && specimen.getSpecimenName().matches("\\d*")){
			error.setErrCode(ErrorCodeEnum.InvalidSpecimenName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	/**
	 * @param specimen
	 * @return
	 */
	public ErrorDTO validateSpecimenDetails(SpecimenDTO specimen) {
		ErrorDTO error = new ErrorDTO();
		if(specimen.getSpecimenName().equals("") && specimen.getSpecimenName()==null && specimen.getSpecimenName().matches("\\d*")){
			error.setErrCode(ErrorCodeEnum.InvalidSpecimenName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if(specimen.getUid()<=0){
			error.setErrCode(ErrorCodeEnum.InvalidSpecimenUid.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	/**
	 * @param uid
	 * @return
	 */
	public ErrorDTO validateSpecimenUid(int uid) {
		ErrorDTO error = new ErrorDTO();
		if(uid<=0){
			error.setErrCode(ErrorCodeEnum.InvalidSpecimenUid.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}
}
