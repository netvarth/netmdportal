/**
 * NetLimsValidator.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.bl.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ActionNameEnum;
import com.nv.youNeverWait.pl.entity.BranchStatusEnum;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.LabUserTypeEnum;
import com.nv.youNeverWait.rs.dto.BranchOrderDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDetail;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.BranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.LabResultHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabUserDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResultTransferDTO;
import com.nv.youNeverWait.rs.dto.TransferNetMdResultDTO;
import com.nv.youNeverWait.rs.dto.UserBranchDTO;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.queryBuilder.BranchPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.LabPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.OrderPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.TransferredOrderPropertyEnum;
import com.nv.youNeverWait.util.filter.queryBuilder.TransferredResultPropertyEnum;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;

/**
 * 
 */
public class LabValidator extends FilterValidator {

	/**
	 * Validate user details
	 * 
	 * @param user
	 * @return error
	 */
	public ErrorDTO validateUser(LabUserDTO user) {

		ErrorDTO error = new ErrorDTO();
		List<Parameter> parameters = new ArrayList<Parameter>();
		if (!user.getBranchIds().isEmpty()) {
			for (UserBranchDTO userBranch : user.getBranchIds()) {
				if (userBranch.getBranchId() == 0) {
					error.setErrCode(ErrorCodeEnum.InvalidBranchId.getErrCode());
					error.setDisplayErrMsg(true);
					return error;
				} else {
					ActionNameEnum.getEnum(userBranch.getActionName());
				}
			}
		}

		/* Setting error message when branch id list is empty */
		else {
			error.setErrCode(ErrorCodeEnum.BranchListEmpty.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (user.getLabId() <= 0) {
			Parameter parameter = new Parameter();
			parameter.setParameterName(Constants.ID);
			parameter.setValue(Integer.toString(user.getLabId()));
			parameters.add(parameter);
			error.setErrCode(ErrorCodeEnum.InvalidLab.getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(true);
			return error;
		}
		if (!isValidName(user.getFirstName())) {
			error.setErrCode(ErrorCodeEnum.InvalidName.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (user.getEmail() != null && !user.getEmail().equals("")) {
			if (!user
					.getEmail()
					.matches(
							"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

				error.setErrCode(ErrorCodeEnum.InvalidMailId.getErrCode());
				error.setDisplayErrMsg(true);
				return error;
			}
		}
		if (user.getPhone() != null && !user.getPhone().equals("")) {
			if (!user.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")) {
				error.setErrCode(ErrorCodeEnum.InvalidPhoneFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		if (user.getMobile() != null && !user.getMobile().equals("")) {
			if (!user.getMobile().matches("\\d{10}")) {
				error.setErrCode(ErrorCodeEnum.InvalidMobileFormat.getErrCode());
				error.setDisplayErrMsg(false);
				return error;
			}
		}
		LabUserTypeEnum.getEnum(user.getUserType());
		if (user.getLogin().getUserType()
				.equals(LabUserTypeEnum.Owner.getDisplayName())
				|| user.getUserType().equals(
						LabUserTypeEnum.Owner.getDisplayName())) {
			error.setErrCode(ErrorCodeEnum.InvalidUserType.getErrCode());
			error.setDisplayErrMsg(false);
			return error;
		}
		return null;
	}

	/**
	 * Validate order filter
	 * 
	 * @param filter
	 * @return ErrorDTO
	 * 
	 */
	public ErrorDTO validateLabFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = LabPropertyEnum.valueOf(exp.getName());
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
	 * Validate netlims account details
	 * 
	 * @param netLims
	 */
	public void validateNetLimsAccount(LabDTO netLims) {

		if (netLims.getName() == null || netLims.getName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidLabName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netLims.getOwnerFirstName() == null
				|| netLims.getOwnerFirstName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOwnerName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netLims.getOwnerEmail() == null
				|| netLims.getOwnerEmail().isEmpty()
				|| !isValidEmail(netLims.getOwnerEmail())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOwnerEmail);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netLims.getHeadOfficeName() == null
				|| netLims.getHeadOfficeName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeadOfficeName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netLims.getHeadOfficeEmail() == null
				|| netLims.getHeadOfficeEmail().isEmpty()
				|| !isValidEmail(netLims.getHeadOfficeEmail())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeadOfficeEmail);
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
	 * 
	 * Validate UserName and password
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
	 * Validte lab branch details
	 * 
	 * @param branch
	 */
	public void validateBranchDetails(LabBranchDTO branch) {
		if (branch.getLabId() <= 0) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getLabId())));
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
		// if(branch.getBranchCode()==null &&
		// branch.getBranchCode().equals("")){
		// ServiceException se = new ServiceException(
		// ErrorCodeEnum.BranchCodeNull);
		// se.setDisplayErrMsg(true);
		// throw se;
		// }
	}

	/**
	 * Validate lab branch id
	 * 
	 * @param branch
	 */
	public void validateBranchId(LabBranchDTO branch) {
		if (branch.getLabId() <= 0) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidLab);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (branch.getGlobalId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Method which return false if value is null/empty
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

	private boolean isValidEmail(String Email) {
		if (Email
				.matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {
			return true;
		}
		return false;
	}

	/**
	 * @param filterDTO
	 * @return
	 */
	public ErrorDTO validateBranchFilter(FilterDTO filterDTO) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filterDTO.getExp()) {
			Property property = null;
			try {
				property = BranchPropertyEnum.valueOf(exp.getName());
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

	public void validateResultDetails(ResultTransferDTO resultTranferDto) {

		if (resultTranferDto.getSourceLabId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSourceLab);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(resultTranferDto.getSourceLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranferDto.getSourceBranchId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSourceBranch);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(resultTranferDto.getSourceBranchId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranferDto.getDestinationLabId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDestinationLab);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(resultTranferDto.getDestinationLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranferDto.getDestinationBranchId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDestinationBranch);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(resultTranferDto.getDestinationBranchId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranferDto.getResult() == null
				|| resultTranferDto.getResult().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.EmptyResult);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranferDto.getOrderUid() == null
				|| resultTranferDto.getOrderUid().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrderUid);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	public void validateNetMdResultDetails(TransferNetMdResultDTO resultTranfer) {

		if (resultTranfer.getSourceLabId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSourceLab);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(resultTranfer.getSourceLabId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranfer.getSourceLabBranchId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSourceBranch);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(resultTranfer.getSourceLabBranchId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (resultTranfer.getDoctorEmail() == null
				|| resultTranfer.getDoctorEmail().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DoctorEmailNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranfer.getResult() == null
				|| resultTranfer.getResult().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.EmptyResult);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranfer.getOrderUid() == null
				|| resultTranfer.getOrderUid().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrderUid);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranfer.getHeader() == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetLimsAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranfer.getPatient() == null) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.PatientDetailNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranfer.getPatient().getFirstName() == null
				|| resultTranfer.getPatient().getFirstName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPatientName);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (resultTranfer.getOrderDate() == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.OrderDateNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DateFormat df = new SimpleDateFormat(

		Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		try {
			df.parse(resultTranfer.getOrderDate());
		} catch (ParseException e) {

			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (resultTranfer.getHeader().getMacId() == null
				|| resultTranfer.getHeader().getMacId().equals("")) {
			ServiceException se = new ServiceException(ErrorCodeEnum.MacIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (resultTranfer.getHeader().getPassPhrase() == null
				|| resultTranfer.getHeader().getPassPhrase().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PassPhraseNull);
			se.setDisplayErrMsg(true);
			throw se;
		}

		validateLabBranchIds(resultTranfer.getHeader().getHeadOfficeId(),
				resultTranfer.getHeader().getBranchId());
	}

	/**
	 * Validating lab header details
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

	public void validateLabBranchIds(int labId, int branchId) {
		if (labId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidLabId);
			se.addParam(new Parameter(Constants.ID, Integer.toString(labId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (branchId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branchId)));
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	public void validateBranchStatus(String status) {
		if (!validateStatus(status)) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchStatus);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	public boolean validateStatus(String status) {
		if (status != null && !status.equals("")) {
			for (BranchStatusEnum branchEnum : BranchStatusEnum.values()) {
				if (branchEnum.getDisplayName().equals(status)) {
					return true;
				}
			}
		}
		return false;
	}

	public void validateOrderDate(BranchOrderDTO orderDTO) {
		String orderFromDate = orderDTO.getFromDate();
		String orderToDate = orderDTO.getToDate();

		if (orderFromDate == null || orderFromDate.equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.FromDateNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (orderToDate == null || orderToDate.equals("")) {
			ServiceException se = new ServiceException(ErrorCodeEnum.ToDateNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!orderFromDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!orderToDate.matches("\\d{4}-\\d{2}-\\d{2}")) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);

		try {
			Date fromDate = df.parse(orderFromDate);
			Date toDate = df.parse(orderToDate);
			if (fromDate.after(toDate)) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidFromToDate);
				se.setDisplayErrMsg(true);
				throw se;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;

		}
	}

	public void validateOrderDetails(BranchOrderDetail branchOrders) {

		if (branchOrders.getLastOrderdTime() == null
				&& branchOrders.getLastOrderdTime().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.OrderedTimeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (branchOrders.getOrderDate() == null
				&& !branchOrders.getOrderDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (branchOrders.getId() <= 0) {
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidId);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	public void validateSystemDefaultDetails(BranchSystemInfoDetails details) {
		if (details.getBranchId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(details
					.getBranchId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (details.getCriticalCpuLevel() == null
				&& details.getCriticalCpuLevel().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.CriticalCpuLevelNull);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if (details.getCriticalHardDiskSpaceLevel() == null
				&& details.getCriticalHardDiskSpaceLevel().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.CriticalHardDiskSPaceLevelNull);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if (details.getCriticalMemoryLevel() == null
				&& details.getCriticalMemoryLevel().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.CriticalMemoryLevelNull);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if (details.getFreqType() == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.FrequencyNull);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if (details.getIntervalTime() == null
				&& details.getIntervalTime().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.IntervalTimeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * @param freqType
	 * @param interval
	 */
	public void validateSyncDetail(String freqType, int interval) {
		if (freqType == null || freqType.equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.SyncFreqNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (interval <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.SyncIntervalTimeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	/**
	 * @param filterDTO
	 * @return
	 */
	public ErrorDTO validateOrderFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = OrderPropertyEnum.valueOf(exp.getName());
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

	public void validateLabResultDetails(LabResultHeaderDTO labResultHeader) {
		validateHeaderDetails(labResultHeader.getHeader());
		validateLabBranchIds(labResultHeader.getHeader().getHeadOfficeId(), labResultHeader.getHeader().getBranchId());
		validateLabBranchIds(labResultHeader.getSourceLabId(), labResultHeader.getSourceLabBranchId()); 
		    if (labResultHeader.getTestUId()== null
					|| labResultHeader.getTestUId().isEmpty()) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidTestUid);
				se.setDisplayErrMsg(true);
				throw se;
			}
		    if (labResultHeader.getResultDetails() == null
					||labResultHeader.getResultDetails().isEmpty()) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.EmptyResult);
				se.setDisplayErrMsg(true);
				throw se;
			}
		
		
		if (labResultHeader.getOrderUid() == null
				|| labResultHeader.getOrderUid().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOrderUid);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
	}

	public ErrorDTO validateTransferredOrderFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = TransferredOrderPropertyEnum.valueOf(exp.getName());
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

	public ErrorDTO validateTransferredResultFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = TransferredResultPropertyEnum.valueOf(exp.getName());
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

}
