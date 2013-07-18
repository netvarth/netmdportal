/**
 * DoctorDetail.java
 *
 * Dec 19, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.DoctorAchievementTbl;
import com.nv.youNeverWait.pl.entity.DoctorEducationalQualificationTbl;
import com.nv.youNeverWait.pl.entity.DoctorExpertiseTbl;
import com.nv.youNeverWait.pl.entity.DoctorMembershipTbl;
import com.nv.youNeverWait.pl.entity.DoctorPracticeExperienceTbl;
import com.nv.youNeverWait.pl.entity.DoctorTbl;

/**
 * 
 */
public class DoctorDetail {
	private int id;
	private int globalId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String workHistory;
	private String mobile;
	private String phone;
	private String email;
	private String address;
	private String workingPlaces;
	private String designation;
	private String specialization;
	private String consultationInterval;
	private String status;
	private String createDateTime;
	private String updateDateTime;
	private List<DoctorExperienceDTO> doctorExperience = new ArrayList<DoctorExperienceDTO>();
	private List<DoctorQualificationDTO> doctorQualifications = new ArrayList<DoctorQualificationDTO>();
	private List<DoctorExpertiseDTO> expertise = new ArrayList<DoctorExpertiseDTO>();
	private List<DoctorMembershipDTO> membership = new ArrayList<DoctorMembershipDTO>();
	private List<DoctorAchievementDTO> achievement = new ArrayList<DoctorAchievementDTO>();
	private ErrorDTO error;
	private boolean success;
	private LoginDTO login;

	
	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	/**
	 * @return the updateDateTime
	 */
	public String getUpdateDateTime() {
		return updateDateTime;
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @param updateDateTime
	 *  the updateDateTime to set
	 */
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *  the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public List<DoctorExpertiseDTO> getExpertise() {
		return expertise;
	}

	public void setExpertise(List<DoctorExpertiseDTO> expertise) {
		this.expertise = expertise;
	}

	public List<DoctorMembershipDTO> getMembership() {
		return membership;
	}

	public void setMembership(List<DoctorMembershipDTO> membership) {
		this.membership = membership;
	}

	public List<DoctorAchievementDTO> getAchievement() {
		return achievement;
	}

	public void setAchievement(List<DoctorAchievementDTO> achievement) {
		this.achievement = achievement;
	}

	/**
	 * @return the login
	 */
	public LoginDTO getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(LoginDTO login) {
		this.login = login;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the globalId
	 */
	public int getGlobalId() {
		return globalId;
	}

	/**
	 * @param globalId
	 *            the globalId to set
	 */
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the workHistory
	 */
	public String getWorkHistory() {
		return workHistory;
	}

	/**
	 * @param workHistory
	 *            the workHistory to set
	 */
	public void setWorkHistory(String workHistory) {
		this.workHistory = workHistory;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the workingPlaces
	 */
	public String getWorkingPlaces() {
		return workingPlaces;
	}

	/**
	 * @param workingPlaces
	 *            the workingPlaces to set
	 */
	public void setWorkingPlaces(String workingPlaces) {
		this.workingPlaces = workingPlaces;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the consultationInterval
	 */
	public String getConsultationInterval() {
		return consultationInterval;
	}

	/**
	 * @param consultationInterval
	 *            the consultationInterval to set
	 */
	public void setConsultationInterval(String consultationInterval) {
		this.consultationInterval = consultationInterval;
	}

	/**
	 * @return the doctorExperience
	 */
	public List<DoctorExperienceDTO> getDoctorExperience() {
		return doctorExperience;
	}

	/**
	 * @param doctorExperience
	 *            the doctorExperience to set
	 */
	public void setDoctorExperience(List<DoctorExperienceDTO> doctorExperience) {
		this.doctorExperience = doctorExperience;
	}

	/**
	 * @return the doctorQualifications
	 */
	public List<DoctorQualificationDTO> getDoctorQualifications() {
		return doctorQualifications;
	}

	/**
	 * @param doctorQualifications
	 *            the doctorQualifications to set
	 */
	public void setDoctorQualifications(
			List<DoctorQualificationDTO> doctorQualifications) {
		this.doctorQualifications = doctorQualifications;
	}

	/**
	 * @return the createDateTime
	 */
	public String getCreateDateTime() {
		return createDateTime;
	}

	/**
	 * @param createDateTime
	 *            the createDateTime to set
	 */
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	/**
	 * @param id
	 * @param globalId
	 * @param firstName
	 * @param lastName
	 * @param dateOfBirth
	 * @param gender
	 * @param workHistory
	 * @param mobile
	 * @param phone
	 * @param email
	 * @param address
	 * @param workingPlaces
	 * @param designation
	 * @param consultationInterval
	 * @param status
	 * @param createDateTime
	 * @param updateDateTime
	 * @param doctorExperience
	 * @param doctorQualifications
	 * @param expertise
	 * @param membership
	 * @param achievement
	 * @param error
	 * @param success
	 * @param login
	 */

	public DoctorDetail(DoctorTbl doctorTbl) {
		super();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		DateFormat sdf = new SimpleDateFormat(Constants.YEAR);
		this.globalId = doctorTbl.getId();
		this.firstName = doctorTbl.getFirstName();
		this.lastName = doctorTbl.getLastName();
		this.dateOfBirth = doctorTbl.getDateOfBirth();
		this.gender = doctorTbl.getGender();
		this.workHistory = doctorTbl.getWorkHistory();
		this.mobile = doctorTbl.getMobile();
		this.phone = doctorTbl.getPhone();
		this.email = doctorTbl.getEmail();
		this.address = doctorTbl.getAddress();
		this.workingPlaces = doctorTbl.getWorkingPlaces();
		this.designation = doctorTbl.getDesignation();
		this.specialization=doctorTbl.getSpecialization();
		this.consultationInterval = Integer.toString(doctorTbl
				.getConsultationInterval());
		this.status = doctorTbl.getStatus();
		this.createDateTime = doctorTbl.getCreateDateTime().toString();
		this.updateDateTime = doctorTbl.getUpdateDateTime().toString();
		
		// doctor experiences
		List<DoctorExperienceDTO> doctorExperiences = new ArrayList<DoctorExperienceDTO>();
		for (DoctorPracticeExperienceTbl doctorPracticeExp : doctorTbl
				.getDoctorPracticeExperienceTbls()) {
			DoctorExperienceDTO doctorExperience = new DoctorExperienceDTO();
			doctorExperience.setDesignation(doctorPracticeExp.getDesignation());
			if(doctorPracticeExp
					.getFromDate()!=null)
			doctorExperience.setFromDate(df.format(doctorPracticeExp
					.getFromDate()));
			if(doctorPracticeExp.getToDate()!=null)
			doctorExperience
					.setToDate(df.format(doctorPracticeExp.getToDate()));
			doctorExperience.setWorkedAt(doctorPracticeExp.getWorkedAt());
			doctorExperience.setId(doctorPracticeExp.getId());
			doctorExperiences.add(doctorExperience);
		}
		// doctor educational qualification
		List<DoctorQualificationDTO> doctorQualifications = new ArrayList<DoctorQualificationDTO>();
		for (DoctorEducationalQualificationTbl doctorEducationQlfcatn : doctorTbl
				.getDoctorEducationalQualificationTbls()) {
			DoctorQualificationDTO doctorQualification = new DoctorQualificationDTO();
			doctorQualification.setEducationalDegree(doctorEducationQlfcatn
					.getEducationalDegree());
			doctorQualification.setInstitution(doctorEducationQlfcatn
					.getInstitution());
			if(doctorEducationQlfcatn.getPassedOutDate()!=null)
				doctorQualification.setPassedOutDate(sdf
					.format(doctorEducationQlfcatn.getPassedOutDate()));
			doctorQualification.setId(doctorEducationQlfcatn.getId());
			doctorQualifications.add(doctorQualification);
		}
		// doctor achievements
		List<DoctorAchievementDTO> doctorAchievements = new ArrayList<DoctorAchievementDTO>();
		for (DoctorAchievementTbl doctorAchievementTbl : doctorTbl
				.getDoctorAchievementTbls()) {
			DoctorAchievementDTO doctorAcheivement = new DoctorAchievementDTO();
			doctorAcheivement.setAchievement(doctorAchievementTbl
					.getAchievement());
			doctorAcheivement.setId(doctorAchievementTbl.getId());
			doctorAchievements.add(doctorAcheivement);
		}
		// doctor expertises
		List<DoctorExpertiseDTO> doctorExpertises = new ArrayList<DoctorExpertiseDTO>();
		for (DoctorExpertiseTbl doctorExpertiseTbl : doctorTbl
				.getDoctorExpertiseTbls()) {
			DoctorExpertiseDTO doctorExpertise = new DoctorExpertiseDTO();
			doctorExpertise.setExpertise(doctorExpertiseTbl.getExpertise());
			doctorExpertise.setId(doctorExpertiseTbl.getId());
			doctorExpertises.add(doctorExpertise);
		}
		// doctor memberships
		List<DoctorMembershipDTO> doctorMemberships = new ArrayList<DoctorMembershipDTO>();
		for (DoctorMembershipTbl doctorMembership : doctorTbl
				.getDoctorMembershipTbls()) {
			DoctorMembershipDTO doctorMembershp = new DoctorMembershipDTO();
			doctorMembershp.setMembership(doctorMembership.getMembership());
			doctorMembershp.setId(doctorMembership.getId());
			doctorMemberships.add(doctorMembershp);
		}
		this.doctorExperience = doctorExperiences;
		this.doctorQualifications = doctorQualifications;
		this.expertise = doctorExpertises;
		this.membership = doctorMemberships;
		this.achievement = doctorAchievements;
		
		LoginDTO login = new LoginDTO();
		if (doctorTbl.getNetmdLoginTbl() != null) {
			login.setUserName(doctorTbl.getNetmdLoginTbl().getUserName());
			login.setUserType(doctorTbl.getNetmdLoginTbl().getUserType());
			login.setPassword(doctorTbl.getNetmdLoginTbl().getPassword());
		}
		this.login = login;

	}

	public DoctorDetail(String a, int b) {

	}

	/**
	 * 
	 */
	public DoctorDetail() {

	}

}
