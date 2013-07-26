package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.pl.entity.DoctorTbl;

public class DoctorDetailsForPatient {
	private int id;
	private String firstName;
	private String lastName;
	private String mobile;
	private String phone;
	private String email;
	private String address;
	private String designation;
	private String specialisation;
	
	public DoctorDetailsForPatient(DoctorTbl doctorTbl) {
		super();
		this.id = doctorTbl.getId();
		this.firstName = doctorTbl.getFirstName();
		this.lastName = doctorTbl.getLastName();
		this.mobile = doctorTbl.getMobile();
		this.phone = doctorTbl.getPhone();
		this.email = doctorTbl.getEmail();
		this.address = doctorTbl.getAddress();
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
