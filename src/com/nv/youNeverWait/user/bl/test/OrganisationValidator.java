/**
 * OrganisationValidator.java
 * @author netvarth
 *
 * Version 1.0 Jan 26, 2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.test;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.DepartmentTypeEnum;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.pl.entity.UserTypeEnum;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.Organisation;
import com.nv.youNeverWait.rs.dto.OrganisationUserDetail;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.queryBuilder.NetMDBranchPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.NetMdPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.OrganisationPropertyEnum;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;

/**
 * 
 * 
 * @author Luciya Jose
 */
public class OrganisationValidator extends FilterValidator {

	/**
	 * @param organztion
	 */
	public void validateOrganisationDetails(Organisation organztion) {

		if (organztion.getDepartmentType() == null
				|| organztion.getDepartmentType().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DepartmentTypeEmpty);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(organztion.getDepartmentType() != null){
			DepartmentTypeEnum departmentEnum=DepartmentTypeEnum.getEnum(organztion.getDepartmentType());
			
		}

		if (organztion.getName() == null || organztion.getName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrganisationName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (organztion.getOwnerFirstName() == null
				|| organztion.getOwnerFirstName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOwnerName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (organztion.getOwnerEmail() == null
				|| organztion.getOwnerEmail().isEmpty()
				|| !isValidEmail(organztion.getOwnerEmail())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOwnerEmail);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (organztion.getHeadOfficeName() == null
				|| organztion.getHeadOfficeName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeadOfficeName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (organztion.getHeadOfficeEmail() == null
				|| organztion.getHeadOfficeEmail().isEmpty()
				|| !isValidEmail(organztion.getHeadOfficeEmail())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeadOfficeEmail);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	/**
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
	 * Validate email format
	 * 
	 * @param Email
	 * @return
	 */
	private boolean isValidEmail(String Email) {
		if (Email
				.matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {
			return true;
		}
		return false;
	}

	/**
	 * @param globalId
	 */
	public void validateGlobalId(int globalId) {
		if (globalId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidGlobalId);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	/**
	 * @param filterDTO
	 * @return
	 */
	public ErrorDTO validateFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = OrganisationPropertyEnum.valueOf(exp.getName());
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
	 * @param organztionUser
	 */
	public void validateUserDetails(OrganisationUserDetail user) {
		if (user.getFirstName() == null
				|| user.getFirstName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (user.getEmail() == null
				|| user.getEmail().isEmpty()
				|| !isValidEmail(user.getEmail())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidEmail);
			se.setDisplayErrMsg(true);
			throw se;
		}
		UserTypeEnum.getEnum(user.getUserType());
			
	}

	/**
	 * @param organztionUser
	 */
	public void validateUserLoginDetails(OrganisationUserDetail user) {
		if (user.getUserName() == null
				|| user.getUserName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (user.getPassword() == null
				|| user.getPassword().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPassword);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
	}
}
