/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.List;

/**
 * @author netvarth
 *
 */
public class PatientInfoDetail {
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
	private List<PatientMedicalHistoryDTO> medicalHistory;
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
	 * @return the updatedDateTime
	 */
	public String getUpdatedDateTime() {
		return updatedDateTime;
	}
	/**
	 * @param updatedDateTime the updatedDateTime to set
	 */
	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	/**
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}
	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	/**
	 * @return the chronicDisease
	 */
	public String getChronicDisease() {
		return chronicDisease;
	}
	/**
	 * @param chronicDisease the chronicDisease to set
	 */
	public void setChronicDisease(String chronicDisease) {
		this.chronicDisease = chronicDisease;
	}
	/**
	 * @return the allergies
	 */
	public String getAllergies() {
		return allergies;
	}
	/**
	 * @param allergies the allergies to set
	 */
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	/**
	 * @return the familyHistory
	 */
	public String getFamilyHistory() {
		return familyHistory;
	}
	/**
	 * @param familyHistory the familyHistory to set
	 */
	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}
	/**
	 * @return the emergencyNo
	 */
	public String getEmergencyNo() {
		return emergencyNo;
	}
	/**
	 * @param emergencyNo the emergencyNo to set
	 */
	public void setEmergencyNo(String emergencyNo) {
		this.emergencyNo = emergencyNo;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
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
	 * @return the medicalHistory
	 */
	public List<PatientMedicalHistoryDTO> getMedicalHistory() {
		return medicalHistory;
	}
	/**
	 * @param medicalHistory the medicalHistory to set
	 */
	public void setMedicalHistory(List<PatientMedicalHistoryDTO> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

}
