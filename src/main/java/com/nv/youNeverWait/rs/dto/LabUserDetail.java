/**
 * LabUserDetail.java
 */
package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.pl.entity.LabUserTbl;

/**
 * @author Luciya Jose
 *
 */
public class LabUserDetail {
	private int globalId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String userType;
	
	
	
	public LabUserDetail(LabUserTbl labUserTbl) {
		this.globalId = labUserTbl.getId();
		this.firstName = labUserTbl.getFirstName();
		this.lastName = labUserTbl.getLastName();
		this.email = labUserTbl.getEmail();
		this.phone = labUserTbl.getPhone();
		this.mobile = labUserTbl.getMobile();
		this.address = labUserTbl.getAddress();
		this.userType = labUserTbl.getUserType();

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
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
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

}
