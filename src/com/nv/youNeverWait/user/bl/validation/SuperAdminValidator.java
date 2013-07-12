/**
 * SuperAdminValidator.java
 *
 * Jan 2, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.bl.validation;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.queryBuilder.LabPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.UserLogPropertyEnum;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;


public class SuperAdminValidator extends FilterValidator {
	
	
	/**
	 * Check validity of passwords
	 * 
	 * @param login
	 * @return ErrorDTO
	 */
	public void validatePasswords(PasswordDTO passwords) {
		if (!isValidExpValue(passwords.getOldPassword())||!isValidExpValue(passwords.getNewPassword())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PasswordNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(passwords.getUsername()==null ||passwords.getUsername().equals("")){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}
	
	/**
	 * Check validity of username and password 
	 * @param login
	 * @return ErrorDTO
	 */
	public ErrorDTO validateLogin(LoginDTO login){
		ErrorDTO error=new ErrorDTO();
		if(!isValidExpValue(login.getUserName())){
			error.setErrCode(ErrorCodeEnum.UserNameNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if(!isValidExpValue(login.getPassword())){
			error.setErrCode(ErrorCodeEnum.PasswordNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}
	/**
	 * Check validity of expression value
	 * @param value
	 * @return boolean
	 *
	 */
	private boolean isValidExpValue(String value) {
		if(value!=null && !value.equals("")){
			return true;
		}
		return false;
	}

	/**
	 * @param filterDTO
	 * @return
	 */
	public ErrorDTO validateLogFilter(FilterDTO filterDTO) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filterDTO.getExp()) {
			Property property = null;
			try{
				property =  UserLogPropertyEnum.valueOf(exp.getName());
			}catch (IllegalArgumentException e) {
				error = getInvalidExpNameError( exp);
				return error;
			}
			error = validateExp(exp,property);
			if(error!=null){
				return error;
			}
		}
		return null;
	}
}
