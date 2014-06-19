/**
 * LabUserDTO.java
 */
package com.nv.NetlimsPortal.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luciya Jose
 *
 */
public class LabUserDTO {
	private int globalId;
	private int id;
	private int labId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String userType;
	private LoginDTO login;
	private List<UserBranchDTO> branchIds = new ArrayList<UserBranchDTO>();
	private boolean success;
	private ErrorDTO error;
	
	
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
	 * @return the labId
	 */
	public int getLabId() {
		return labId;
	}
	/**
	 * @param labId the labId to set
	 */
	public void setLabId(int labId) {
		this.labId = labId;
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
	 * @return the branchIds
	 */
	public List<UserBranchDTO> getBranchIds() {
		return branchIds;
	}
	/**
	 * @param branchIds the branchIds to set
	 */
	public void setBranchIds(List<UserBranchDTO> branchIds) {
		this.branchIds = branchIds;
	}
	/**
	 * 
	 */
	public LabUserDTO() {
		
	}

	public LabUserDTO(int globalId, int id, int labId, String firstName,
			String lastName, String email, String phone, String mobile,
			String address, String userType, LoginDTO login,
			List<UserBranchDTO> branchIds, boolean success, ErrorDTO error) {
		super();
		this.globalId = globalId;
		this.id = id;
		this.labId = labId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.mobile = mobile;
		this.address = address;
		this.userType = userType;
		this.login = login;
		this.branchIds = branchIds;
		this.success = success;
		this.error = error;
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
