package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.pl.entity.DoctorTbl;

public class DoctorDetailsForPatient {
	private int id;
	private String firstName;
	private String lastName;
//	private String dateOfBirth ;
//	private String gender;
//	private String expertise;
//	private String workHistory;
	private String mobile;
	private String phone;
	private String email;
	private String address;
//	private String workingPlaces;
//	private String memberships;
//	private String achievements;
	private String designation;
	private String specialisation;
	
	public DoctorDetailsForPatient(DoctorTbl doctorTbl) {
		super();
		this.id = doctorTbl.getId();
		this.firstName = doctorTbl.getFirstName();
		this.lastName = doctorTbl.getLastName();
		//this.gender = doctorTbl.getGender();
		//this.expertise = doctorTbl.getExpertise();
		//this.workHistory = doctorTbl.getWorkHistory();
		this.mobile = doctorTbl.getMobile();
		this.phone = doctorTbl.getPhone();
		this.email = doctorTbl.getEmail();
		this.address = doctorTbl.getAddress();
		//this.memberships = doctorTbl.getMemberships();
	//	this.achievements = doctorTbl.getAchievements();
		this.designation = doctorTbl.getDesignation();
		this.specialisation = doctorTbl.getSpecialization();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
//	public String getDateOfBirth() {
//		return dateOfBirth;
//	}
//	public void setDateOfBirth(String dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}
//	public String getGender() {
//		return gender;
//	}
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//	public String getExpertise() {
//		return expertise;
//	}
//	public void setExpertise(String expertise) {
//		this.expertise = expertise;
//	}
//	public String getWorkHistory() {
//		return workHistory;
//	}
//	public void setWorkHistory(String workHistory) {
//		this.workHistory = workHistory;
//	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	public String getWorkingPlaces() {
//		return workingPlaces;
//	}
//	public void setWorkingPlaces(String workingPlaces) {
//		this.workingPlaces = workingPlaces;
//	}
//	public String getMemberships() {
//		return memberships;
//	}
//	public void setMemberships(String memberships) {
//		this.memberships = memberships;
//	}
//	public String getAchievements() {
//		return achievements;
//	}
//	public void setAchievements(String achievements) {
//		this.achievements = achievements;
//	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}
	
	
}
