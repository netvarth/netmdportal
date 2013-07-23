/**
 * AuthenticationValidator.java
 * 
 * @Author Linu Paul
 *
 * Version 1.0 aug 22, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.youNeverWait.security.validation;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.rs.dto.CaptchaVerificationDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;

public class AuthenticationValidator {
	/**
	 * Check validity of username and password
	 * 
	 * @param login
	 * @return ErrorDTO
	 */
	public ErrorDTO validateLogin(LoginDTO login) {
		ErrorDTO error = new ErrorDTO();
		if (!isValidExpValue(login.getUserName())) {
			error.setErrCode(ErrorCodeEnum.UserNameNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidExpValue(login.getPassword())) {
			error.setErrCode(ErrorCodeEnum.PasswordNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}

	/**
	 * Check validity of username and userType
	 * 
	 * @param login
	 * @return ErrorDTO
	 */
	public void validateNetlimsUser(String userName) {
		if (!isValidExpValue(userName)) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNameNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Check validity of username and userType
	 * 
	 * @param login
	 * @return ErrorDTO
	 */
	public void validateNetmdUser(String userName, String userType) {
		if (!isValidExpValue(userName)) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNameNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidExpValue(userType)) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserTypeNull);
			se.setDisplayErrMsg(true);
			throw se;
		} else {
			NetmdUserTypeEnum type = NetmdUserTypeEnum.getEnum(userType);
		}
	}
	/**
	 * Check validity of username and userType
	 * 
	 * @param login
	 * @return ErrorDTO
	 */
	public void validatePatient(String userName, String userType) {
		if (!isValidExpValue(userName)) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNameNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidExpValue(userType)) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserTypeNull);
			se.setDisplayErrMsg(true);
			throw se;
		} else {
			NetmdUserTypeEnum type = NetmdUserTypeEnum.getEnum(userType);
		}
	}

	/**
	 * Check validity of expression value
	 * 
	 * @param value
	 * @return boolean
	 * 
	 */
	private boolean isValidExpValue(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}

	public void validateCaptcha(CaptchaVerificationDTO captcha) {
		if (captcha.getSecretCode() == null
				|| captcha.getSecretCode().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSecretCode);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (captcha.getVerificationCode() == null
				|| captcha.getVerificationCode().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidVerificationCode);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}
}
