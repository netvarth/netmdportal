package com.nv.youNeverWait.user.bl.validation;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;

public class FacilityValidator {

	/**
	 * Validates userName and password
	 * 
	 * @param userName
	 * @param password
	 */
	public void validateUserNameAndPassword(String name) {
	
		if (name == null || name.isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}
}
