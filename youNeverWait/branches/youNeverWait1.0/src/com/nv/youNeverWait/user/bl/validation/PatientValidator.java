/**
 * 
 */
package com.nv.youNeverWait.user.bl.validation;

import java.util.ArrayList;
import java.util.List;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.queryBuilder.NetMDBranchPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.ResultPropertyEnum;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;

/**
 * @author Luciya Jose
 *
 */
public class PatientValidator extends FilterValidator{
	public ErrorDTO validateCreatePatient(PatientDetail patient, HeaderDTO header){

		ErrorDTO error=new ErrorDTO();
		ValidateHeaderDetails(header);

		if(!isValidName(patient.getFirstName())){
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
//		if(patient.getLogin().getUserName()!=null && !patient.getLogin().getUserName().equals("")){
//			if(!patient.getEmail().matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")){
//
//				error.setErrCode(ErrorCodeEnum.InvalidUserId.getErrCode());
//				error.setDisplayErrMsg(true);
//				return error;
//			}
//		}
		if(patient.getEmail()!=null && !patient.getEmail().equals("")){
			if(!patient.getEmail().matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")){

				error.setErrCode(ErrorCodeEnum.InvalidMailId.getErrCode());
				error.setDisplayErrMsg(true);
				return error;
			}
		}
		if(patient.getPhone()!=null && !patient.getPhone().equals("")){
			if(!patient.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")){
				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		if(patient.getMobile()!=null && !patient.getMobile().equals("")){
			if(!patient.getMobile().matches("\\d{10}")){
				error.setErrCode(ErrorCodeEnum.InvalidMobileFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
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

	public ErrorDTO validateUpdatePatient(PatientDetail patient, HeaderDTO header){
		ErrorDTO error=new ErrorDTO();
		ValidateGlobalId(patient.getGlobalId(), header);

		if(!isValidName(patient.getFirstName())){
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if(patient.getEmail()!=null && !patient.getEmail().equals("")){
			if(!patient.getEmail().matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")){

				error.setErrCode(ErrorCodeEnum.InvalidMailId.getErrCode());
				error.setDisplayErrMsg(true);
				return error;
			}
		}
		if(patient.getPhone()!=null && !patient.getPhone().equals("")){
			if(!patient.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")){
				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		if(patient.getMobile()!=null && !patient.getMobile().equals("")){
			if(!patient.getMobile().matches("\\d{10}")){
				error.setErrCode(ErrorCodeEnum.InvalidMobileFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		return null;
	}

	private boolean isValidName(String value) {
		if(value!=null && !value.equals("")){
			return true;
		}
		return false;
	}
	public ErrorDTO ValidateCreateUser(LoginDTO user){
		ErrorDTO error = new ErrorDTO();

		if(!isValidName(user.getUserName())){
			error.setErrCode(ErrorCodeEnum.UserNameNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		if(!isValidName(user.getPassword())){
			error.setErrCode(ErrorCodeEnum.PasswordNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}
	private ErrorDTO ValidateHeaderDetails(HeaderDTO header)
	{
		ErrorDTO error = new ErrorDTO();
		if(header.getNetMdId()==0)
		{
			error.setErrCode(ErrorCodeEnum.NetMdIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if(header.getNetMdBranchId()==0)
		{
			error.setErrCode(ErrorCodeEnum.InvalidBranchId.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if(header.getPassPhrase()==null ||header.getPassPhrase().equals(""))
		{
			error.setErrCode(ErrorCodeEnum.PassPhraseNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if(header.getMacId()==null ||header.getMacId().equals(""))
		{
			error.setErrCode(ErrorCodeEnum.MacIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	public ErrorDTO ValidateGlobalId(int globalId, HeaderDTO header){
		ErrorDTO error = new ErrorDTO();
		ValidateHeaderDetails(header);
		if(globalId==0){
			error.setErrCode(ErrorCodeEnum.InvalidGlobalId.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	/**
	 * Validate clinic filter
	 * 
	 * @param filter
	 * @return ErrorDTO
	 */
	public ErrorDTO validateResultFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = ResultPropertyEnum.valueOf(exp.getName());
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
	public void validatePatientOrderDetails(PatientOrderDTO patient) {
		if(patient.getPatientId()<=0){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if(!isValidName(patient.getOrderId())){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrderUid);
			se.setDisplayErrMsg(true);
			throw se;
		}	
	}
}
