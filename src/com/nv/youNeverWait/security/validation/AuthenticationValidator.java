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
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;

import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;


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
	 * Validate login details
	 * 
	 * @param userName
	 * @param password
	 */
	public void validateUserNameAndPassword(String userName, String password) {
		if (userName == null || userName.isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (password == null || password.isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPassword);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}
	/**
	 * Check validity of passwords
	 * 
	 * @param login
	 * @return ErrorDTO
	 */
	public void validatePasswords(PasswordDTO passwords) {
		if (!isValidExpValue(passwords.getOldPassword())
				|| !isValidExpValue(passwords.getNewPassword())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PasswordNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (passwords.getUsername() == null
				|| passwords.getUsername().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}
	/**
	 * Check validity of passwords
	 * 
	 * @param login
	 * @return ErrorDTO
	 */
	public void validatePasswordsForCreatePassword(CreatePasswordDTO createPasswordDto) {
		if (!isValidExpValue(createPasswordDto.getPassword())
				|| !isValidExpValue(createPasswordDto.getConfirmPassword())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PasswordNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (createPasswordDto.getUsername() == null
				|| createPasswordDto.getUsername().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
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
