/**
 * DoctorValidator.java
 *
 * Dec 19, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.bl.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.rs.dto.DoctorAchievementDTO;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.DoctorExperienceDTO;
import com.nv.youNeverWait.rs.dto.DoctorExpertiseDTO;
import com.nv.youNeverWait.rs.dto.DoctorMembershipDTO;
import com.nv.youNeverWait.rs.dto.DoctorQualificationDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.Parameter;

public class DoctorValidator {

	/**
	 * Validates userName and password
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
	 * Validates doctor details
	 * 
	 * @param doctor
	 * @param header
	 */
	public void validateCreateDoctor(DoctorDetail doctor, HeaderDTO header) {

		validateHeaderDetails(header);
		if (!isValidName(doctor.getFirstName())) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidName(doctor.getEmail())) {

			ServiceException se = new ServiceException(ErrorCodeEnum.EmailNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (doctor.getEmail() != null && !doctor.getEmail().equals("")) {
			if (!doctor
					.getEmail()
					.matches(
							"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidMailId);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (doctor.getPhone() != null && !doctor.getPhone().equals("")) {
			if (!doctor.getPhone().matches("^0?[1-9]{1}[0-9]{9}$")) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidPhoneFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if (doctor.getMobile() != null && !doctor.getMobile().equals("")) {
			if (!doctor.getMobile().matches("\\d{10}")) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidMobileFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		
		if (doctor.getDateOfBirth() != null) {
			if (!doctor.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}

			DateFormat df = new SimpleDateFormat(
					Constants.DATE_FORMAT_WITHOUT_TIME);
			try {
				Date dob = df.parse(doctor.getDateOfBirth());
			} catch (ParseException e) {

				e.printStackTrace();
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}

		if (!doctor.getDoctorExperience().isEmpty()) {
			for (DoctorExperienceDTO doctorExperience : doctor
					.getDoctorExperience()) {

				// Validate doctor experience details
				validateDoctorExperience(doctorExperience);
			}
		}
		if (!doctor.getDoctorQualifications().isEmpty()) {
			for (DoctorQualificationDTO doctorQualificaton : doctor
					.getDoctorQualifications()) {

				// Validate doctor educational qualification details
				validateDoctorQualification(doctorQualificaton);
			}
		}
		if (!doctor.getAchievement().isEmpty()) {
			for (DoctorAchievementDTO doctorAchievement : doctor
					.getAchievement()) {

				// Validate doctor achievemnet details
				validateDoctorAchievement(doctorAchievement);
			}
		}
		if (!doctor.getMembership().isEmpty()) {
			for (DoctorMembershipDTO doctorMembership : doctor.getMembership()) {
				// Validate doctor membership details
				validateDoctorMembership(doctorMembership);
			}
		}
		if (!doctor.getExpertise().isEmpty()) {
			for (DoctorExpertiseDTO doctorExpertise : doctor.getExpertise()) {

				// Validate doctor membership details
				validateDoctorExpertise(doctorExpertise);
			}
		}
	}

	/**
	 * Validates doctor qualification details
	 * 
	 * @param doctorQualificaton
	 */
	public void validateDoctorQualification(
			DoctorQualificationDTO doctorQualificaton) {
		if (doctorQualificaton.getPassedOutDate() == null
				|| !doctorQualificaton.getPassedOutDate().matches(
						"\\d{4}")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (!isValidName(doctorQualificaton.getEducationalDegree())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}
		if (!isValidName(doctorQualificaton.getInstitution())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidName);
			se.setDisplayErrMsg(true);
			throw se;

		}
	}

	/**
	 * Validates doctor experience details
	 * 
	 * @param doctorExperience
	 */
	public void validateDoctorExperience(DoctorExperienceDTO doctorExperience) {

		if (doctorExperience.getFromDate() != null
				&& doctorExperience.getToDate() != null) {
			if (!doctorExperience.getFromDate().matches("\\d{4}-\\d{2}-\\d{2}")
					|| !doctorExperience.getToDate().matches(
							"\\d{4}-\\d{2}-\\d{2}")) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}

			DateFormat df1 = new SimpleDateFormat(
					Constants.DATE_FORMAT_WITHOUT_TIME);
			Date fromDate;
			Date toDate;
			try {
				fromDate = df1.parse(doctorExperience.getFromDate());
				toDate = df1.parse(doctorExperience.getToDate());
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

	}

	/**
	 * Validates doctor acheivement details
	 * 
	 * @param doctorAchievement
	 */
	public void validateDoctorAchievement(DoctorAchievementDTO doctorAchievement) {

		if (!isValidName(doctorAchievement.getAchievement())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidAchievement);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	/**
	 * Validates doctor membership details
	 * 
	 * @param doctorMembership
	 */
	public void validateDoctorMembership(DoctorMembershipDTO doctorMembership) {

		if (!isValidName(doctorMembership.getMembership())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidMemberShip);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Validates doctor expertise details
	 * 
	 * @param doctorExpertise
	 */
	public void validateDoctorExpertise(DoctorExpertiseDTO doctorExpertise) {

		if (!isValidName(doctorExpertise.getExpertise())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidExpertise);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Method performed to validate login details
	 * 
	 * @param user
	 * @return ErrorDTO
	 */
	public void validateCreateUser(LoginDTO user) {

		if (!isValidName(user.getUserName())) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNameNull);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (!isValidName(user.getPassword())) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.PasswordNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!isValidName(user.getUserType())) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserTypeNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdUserTypeEnum usertype= NetmdUserTypeEnum.getEnum(user.getUserType());
	}

	/**
	 * Method to validte doctor details for updation
	 * 
	 * @param doctor
	 * @param header
	 * @return ErrorDTO
	 */
	public void validateUpdateDoctor(DoctorDetail doctor, HeaderDTO header) {

		if (doctor.getGlobalId() <= 0) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidGlobalId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		validateCreateDoctor(doctor, header);
	}

	/**
	 * Method for null checking
	 * 
	 * @param value
	 */
	private boolean isValidName(String value) {
		if (value != null && !value.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * Method to validate header details
	 * 
	 * @param header
	 * @return ErrorDTO
	 */
	public void validateHeaderDetails(HeaderDTO header) {

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
		if (header.getMacId() == null || header.getMacId().equals("")) {

			ServiceException se = new ServiceException(ErrorCodeEnum.MacIdNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Method to validate global Id and header details
	 * 
	 * @param globalId
	 * @param header
	 * @return ErrorDTO
	 */
	public void validateGlobalId(int globalId) {
		if (globalId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidGlobalId);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}
}
