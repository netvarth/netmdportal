/**
 * NetRxValidator.java
 *
 * @Author Luciya Jos
 * May 8, 2013 
 */
package com.nv.youNeverWait.user.bl.validation;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchDetail;
import com.nv.youNeverWait.rs.dto.NetRxDTO;
import com.nv.youNeverWait.rs.dto.NetRxHeaderDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserDetail;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.queryBuilder.NetRxBranchPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.NetRxUserPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.NetRxPropertyEnum;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;
import com.nv.youNeverWait.pl.entity.NetRxUserTypeEnum;
/**
 * @author netvarth
 *
 */
public class NetRxValidator extends FilterValidator{
	/**
	 * Validate user details
	 * 
	 * @param netRxUser
	 */
	public void validateUserDetails(NetRxHeaderDTO header,NetRxUserDetail user) {
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
		if (header.getNetRxId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMd);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header.getNetRxId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getNetRxBranchId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRxBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (user.getFirstName() == null
				|| user.getFirstName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRxUserName);
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
		NetRxUserTypeEnum.getEnum(user.getUserType());
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
	 * Method to validate header details
	 * 
	 * @param header
	 * @return ErrorDTO
	 */
	public void validateHeader(NetRxHeaderDTO header) {

		if (header.getNetRxId() <= 0) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetRxIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getPassPhrase() == null || header.getPassPhrase().equals("")) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.PassPhraseNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getNetRxBranchId() <= 0) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchMissMatch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header.getNetRxBranchId())));
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
	public void validateGlobalId(int globalId) {
		if(globalId<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidGlobalId);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		
	}
	public void validateNetRxUserLoginDetails(NetRxUserDetail user){

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
	
	public void validateNetRxUserCreation(NetRxUserDetail user){

		if (user.getUserName() == null
				|| user.getUserName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
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
	public ErrorDTO validateNetRxFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = NetRxPropertyEnum.valueOf(exp.getName());
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
	 * Validate all netrx details
	 * 
	 * @param netRx
	 * @return service exception if there is any invalid data
	 */
	public void validateNetRxAccount(NetRxDTO netRx) {

		if (netRx.getName() == null || netRx.getName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRxName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netRx.getOwnerFirstName() == null
				|| netRx.getOwnerFirstName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOwnerName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netRx.getOwnerEmail() == null || netRx.getOwnerEmail().isEmpty()
				|| !isValidEmail(netRx.getOwnerEmail())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOwnerEmail);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netRx.getHeadOfficeName() == null
				|| netRx.getHeadOfficeName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeadOfficeName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netRx.getHeadOfficeEmail() == null
				|| netRx.getHeadOfficeEmail().isEmpty()
				|| !isValidEmail(netRx.getHeadOfficeEmail())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeadOfficeEmail);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netRx.getOwnerPhone() != null && !netRx.getOwnerPhone().equals("")) {
			if (!netRx.getOwnerPhone().matches("^0?[1-9]{1}[0-9]{9}$")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidOwnerPhoneFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (netRx.getOwnerMobile() != null && !netRx.getOwnerMobile().equals("")) {
			if (!netRx.getOwnerMobile().matches("\\d{10}")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidOwnerMobileFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (netRx.getHeadOfficePhone() != null && !netRx.getHeadOfficePhone().equals("")) {
			if (!netRx.getHeadOfficePhone().matches("^0?[1-9]{1}[0-9]{9}$")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidHeadOfficePhoneFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (netRx.getHeadOfficeMobile() != null && !netRx.getHeadOfficeMobile().equals("")) {
			if (!netRx.getHeadOfficeMobile().matches("\\d{10}")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidHeadOfficeMobileFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
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
	 * Validate NetRx user filter
	 * 
	 * @param filter
	 * @return ErrorDTO
	 */
	public ErrorDTO validateNetRxUserFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = NetRxUserPropertyEnum.valueOf(exp.getName());
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
	 * Validate NetRx branch filter
	 * 
	 * @param filter
	 * @return ErrorDTO
	 */
	public ErrorDTO validateNetRxBranchFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = NetRxBranchPropertyEnum.valueOf(exp.getName());
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
	public void validateBranchDetails(NetRxBranchDetail branch) {
		
		if(branch.getNetRxId()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRx);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getNetRxId())));
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
//		if (branch.getEmail() == null || branch.getEmail() .isEmpty()|| !isValidEmail(branch.getEmail() )) {
//			ServiceException se = new ServiceException(
//					ErrorCodeEnum.InvalidEmail);
//			se.setDisplayErrMsg(true);
//			throw se;
//		}
	}
	
	/**
	 * Validates netrx branch details for updation
	 */
	public void validateUpdateBranchDetails(NetRxBranchDetail branch){
		if(branch.globalId<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch.globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		validateBranchDetails(branch);
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
}
