/**
 * NetMdValidator.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.bl.validation;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDetail;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.NetMdDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.queryBuilder.NetMDBranchPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.NetMdPropertyEnum;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;

public class NetMdValidator extends FilterValidator {

	/**
	 * Validate clinic filter
	 * 
	 * @param filter
	 * @return ErrorDTO
	 */
	public ErrorDTO validateClinicFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = NetMDBranchPropertyEnum.valueOf(exp.getName());
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
	 * Validate branch details
	 * 
	 * @param branch
	 */
	public void validateBranchDetails(NetMdBranchDTO branch) {
		if (branch.getNumberOfDevices() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNumberOfDevices);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getNetMdId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (branch.getNetMdId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMd);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getNetMdId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidName(branch.getName())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (branch.getPhone() != null && !branch.getPhone().equals("")) {
			if (!branch.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidPhoneFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (branch.getMobile() != null && !branch.getMobile().equals("")) {
			if (!branch.getMobile().matches("\\d{10}")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidMobileFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
	}
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
	 * Validate branch id
	 * 
	 * @param branch
	 */
	public void validateBranchId(NetMdBranchDTO branch) {
		if (branch.getNetMdId() == 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMd);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getNetMdId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (branch.getGlobalId() == 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Validate name
	 * 
	 * @param value
	 * @return
	 */
	private boolean isValidName(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
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
	 * Validate all netmd details
	 * 
	 * @param netMd
	 * @return service exception if there is any invalid data
	 */
	public void validateNetMdAccount(NetMdDTO netMd) {

		if (netMd.getName() == null || netMd.getName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netMd.getOwnerFirstName() == null
				|| netMd.getOwnerFirstName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOwnerName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netMd.getOwnerEmail() == null || netMd.getOwnerEmail().isEmpty()
				|| !isValidEmail(netMd.getOwnerEmail())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOwnerEmail);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netMd.getHeadOfficeName() == null
				|| netMd.getHeadOfficeName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeadOfficeName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netMd.getHeadOfficeEmail() == null
				|| netMd.getHeadOfficeEmail().isEmpty()
				|| !isValidEmail(netMd.getHeadOfficeEmail())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeadOfficeEmail);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Validate header details
	 * 
	 * @param header
	 */
	public void validateHeaderDetails(HeaderDTO header) {
		if (header.getMacId() == null || header.getMacId().equals("")) {
			ServiceException se = new ServiceException(ErrorCodeEnum.MacIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getPassPhrase() == null || header.getPassPhrase().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PassPhraseNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Validate user details
	 * 
	 * @param netMdUser
	 */
	public void validateUserDetails(HeaderDTO header,NetMdUserDetail user) {
		if (header == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeader);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (user == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUser);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getNetMdId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMd);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header.getNetMdId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getNetMdBranchId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
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
		NetmdUserTypeEnum.getEnum(user.getUserType());
	}

	public void validateNetMdUserLoginDetails(NetMdUserDetail user){

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
	/**
	 * Validate filter details
	 * 
	 * @param filterDTO
	 * @return
	 */
	public ErrorDTO validateNetMdFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = NetMdPropertyEnum.valueOf(exp.getName());
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

	public void validateGlobalId(int globalId) {
		if(globalId<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidGlobalId);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		
	}
	/**
	 * Validate Netmd id
	 * @param netMdId
	 */
	public void validateNetmdId(int netMdId) {
		if(netMdId<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdId);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netMdId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

		/**
		 * Method to validate header details
		 * 
		 * @param header
		 * @return ErrorDTO
		 */
		public void validateHeader(HeaderDTO header) {

			if (header.getNetMdId() <= 0) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.NetMdIdNull);
				se.setDisplayErrMsg(true);
				throw se;
			}
			if (header.getPassPhrase() == null || header.getPassPhrase().equals("")) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.PassPhraseNull);
				se.setDisplayErrMsg(true);
				throw se;
			}
			if (header.getNetMdBranchId() <= 0) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.BranchMissMatch);
				se.addParam(new Parameter(Constants.ID, Integer.toString(header.getNetMdBranchId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}

}
