/**
 * PatientValidator.java
 */
package com.nv.youNeverWait.user.bl.validation;


import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ActionNameEnum;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.CaseDTO;
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.queryBuilder.ResultPropertyEnum;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;

/**
 * @author Luciya Jose
 *
 */
public class PatientValidator extends FilterValidator{
	public void validateCreatePatient(PatientDetail patient, HeaderDTO header){

		ValidateHeaderDetails(header);  // validates header info
		if(!isValidName(patient.getFirstName())){
			
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if(patient.getEmail()!=null && !patient.getEmail().equals("")){
			if(!patient.getEmail().matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")){

				
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidMailId);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if(patient.getPhone()!=null && !patient.getPhone().equals("")){
			if(!patient.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")){
				
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidPhoneFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if(patient.getMobile()!=null && !patient.getMobile().equals("")){
			if(!patient.getMobile().matches("\\d{10}")){
				
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidMobileFormat);
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

	/**
	 * Validate details of patient
	 * @param patient
	 * @param header
	 * @return
	 */
	public void validateUpdatePatient(PatientDetail patient, HeaderDTO header){
	
		ValidateGlobalId(patient.getGlobalId(), header);

		if(!isValidName(patient.getFirstName())){
			
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(patient.getEmail()!=null && !patient.getEmail().equals("")){
			if(!patient.getEmail().matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")){

				
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidMailId);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if(patient.getPhone()!=null && !patient.getPhone().equals("")){
			if(!patient.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")){
				
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidPhoneFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if(patient.getMobile()!=null && !patient.getMobile().equals("")){
			if(!patient.getMobile().matches("\\d{10}")){
				
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidMobileFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		
	}

	private boolean isValidName(String value) {
		if(value!=null && !value.equals("")){
			return true;
		}
		return false;
	}
	/**
	 * Validates user details
	 * @param user
	 * @return error
	 */
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
	/**
	 * Validates header details
	 * @param header
	 * @return error
	 */
	private ErrorDTO ValidateHeaderDetails(HeaderDTO header)
	{
		ErrorDTO error = new ErrorDTO();
		if(header.getHeadOfficeId()==0)
		{
			error.setErrCode(ErrorCodeEnum.NetMdIdNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if(header.getBranchId()==0)
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

	/**
	 * Validte id of patient
	 * @param globalId
	 * @param header
	 * @return
	 */
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
	 * Validate result filter
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
	/**
	 * Validate patient order details
	 * @param patient
	 */
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
	/**
	 * @param newPatientCase
	 */
	public void validateCase(CaseDTO caseDto) {
		
		if (!isValidName(caseDto.getCaseName())) {
			ServiceException se=new ServiceException(ErrorCodeEnum.InValidCaseName);
			se.isDisplayErrMsg();
			throw se;
		}
		if (caseDto.getPatientId() <=0) {
			ServiceException se=new ServiceException(ErrorCodeEnum.InvalidPatientId);
			se.isDisplayErrMsg();
			throw se;
		}
		if (caseDto.getDepartmentId() <=0) {
			ServiceException se=new ServiceException(ErrorCodeEnum.InvalidDepartmentId);
			se.isDisplayErrMsg();
			throw se;
		}
	}
	public void validateUpdatedCase(CaseDTO updatedPatientCase) {
		if(updatedPatientCase.getGlobalId()<=0){
			ServiceException se=new ServiceException(ErrorCodeEnum.InvalidCaseId);
			se.isDisplayErrMsg();
			throw se;
		}
		ActionNameEnum action=ActionNameEnum.getEnum(updatedPatientCase.getActionName());
		
	}
}
