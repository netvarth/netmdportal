/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.PatientTbl;

/**
 * @author Luciya Jose
 *
 */
public class PatientDetail {
	private int id;
	private int globalId;
	private String firstName;
	private String lastName;
	private int age ;
	private String gender;
	private String mobile;
	private String phone;
	private String address;
	private String email;
	private String ailment;
	private String diagnosis;
	private String createDateTime;
	private String updatedDateTime;	
	private String bloodGroup;
	private String height;
	private String weight;
	private String chronicDisease;	
	private String allergies;
	private String familyHistory;
	private String emergencyNo;
	private String status;
	private int branchId;
	private LoginDTO login;
	private boolean success;
	private ErrorDTO error;
	private List<PatientMedicalHistoryDTO> medicalHistory=new ArrayList<PatientMedicalHistoryDTO>();
	private List<CaseResponseDTO>caseResponseList=new ArrayList<CaseResponseDTO>();
	public List<CaseResponseDTO> getCaseResponseList() {
		return caseResponseList;
	}
	public void setCaseResponseList(List<CaseResponseDTO> caseResponseList) {
		this.caseResponseList = caseResponseList;
	}
	public PatientDetail(PatientTbl patientTbl) {
		super();
		DateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		this.id = patientTbl.getId();
		this.globalId = patientTbl.getId();
		this.firstName = patientTbl.getFirstName();
		this.lastName = patientTbl.getLastName();
		this.age = patientTbl.getAge();
		this.gender = patientTbl.getGender();
		this.mobile = patientTbl.getMobile();
		this.phone = patientTbl.getPhone();
		this.address = patientTbl.getAddress();
		this.email = patientTbl.getEmail();
		this.ailment = patientTbl.getAilment();
		this.diagnosis = patientTbl.getDiagnosis();
		this.createDateTime = sdf.format(patientTbl.getCreateDateTime());
		this.updatedDateTime = sdf.format(patientTbl.getUpdateDateTime());
		this.bloodGroup = patientTbl.getBloodGroup();
		this.height = patientTbl.getHeight();
		this.weight = patientTbl.getWeight();
		this.chronicDisease = patientTbl.getChronicDisease();
		this.allergies = patientTbl.getAllergies();
		this.familyHistory = patientTbl.getFamilyHistory();
		this.emergencyNo = patientTbl.getEmergencyNo();
		this.status = patientTbl.getStatus();
		this.branchId = patientTbl.getNetmdBranchTbl().getId();
		
	}
	public String getChronicDisease() {
		return chronicDisease;
	}
	public void setChronicDisease(String chronicDisease) {
		this.chronicDisease = chronicDisease;
	}
	public List<PatientMedicalHistoryDTO> getMedicalHistory() {
		return medicalHistory;
	}
	public void setMedicalHistory(List<PatientMedicalHistoryDTO> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	
	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	/**
	 * @return the createDateTime
	 */
	public String getCreateDateTime() {
		return createDateTime;
	}
	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	/**
	 * @return the login
	 */
	public LoginDTO getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
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
	 * @param id the id to set
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
	 * @param globalId the globalId to set
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
	 * @param firstName the firstName to set
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
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
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
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the ailment
	 */
	public String getAilment() {
		return ailment;
	}
	/**
	 * @param ailment the ailment to set
	 */
	public void setAilment(String ailment) {
		this.ailment = ailment;
	}
	/**
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}
	/**
	 * @param diagnosis the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	
	/**
	 * @param id
	 * @param globalId
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param mobile
	 * @param phone
	 * @param address
	 * @param email
	 * @param ailment
	 * @param diagnosis
	 * @param createDateTime
	 * @param login
	 * @param success
	 * @param error
	 */
	public PatientDetail(int id, int globalId, String firstName,
			String lastName, int age, String gender, String mobile,
			String phone, String address, String email, String ailment,
			String diagnosis, String createDateTime, LoginDTO login,
			boolean success, ErrorDTO error) {
		super();
		this.id = id;
		this.globalId = globalId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.mobile = mobile;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.ailment = ailment;
		this.diagnosis = diagnosis;
		this.createDateTime = createDateTime;
		this.login = login;
		this.success = success;
		this.error = error;
	}
	/**
	 * 
	 */
	public PatientDetail() {
		
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	public String getFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}
	public String getEmergencyNo() {
		return emergencyNo;
	}
	public void setEmergencyNo(String emergencyNo) {
		this.emergencyNo = emergencyNo;
	}
	public String getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	

}
