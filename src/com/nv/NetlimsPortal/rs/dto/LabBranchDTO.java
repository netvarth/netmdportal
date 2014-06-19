/**
 * NetLimsBranchDTO.java
 *
 * Jan 2, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.NetlimsPortal.rs.dto;

/**
 * 
 */
public class LabBranchDTO {
	public int globalId;
	private int id;
	private String name;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private int labId;
	private String status;
	private String macId;
	private String passPhrase;
	private boolean homeBranch;
//	private String branchCode;
	
	
	
//	/**
//	 * @return the branchCode
//	 */
//	public String getBranchCode() {
//		return branchCode;
//	}
//	/**
//	 * @param branchCode the branchCode to set
//	 */
//	public void setBranchCode(String branchCode) {
//		this.branchCode = branchCode;
//	}
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
	 * @return the macId
	 */
	public String getMacId() {
		return macId;
	}
	/**
	 * @param macId the macId to set
	 */
	public void setMacId(String macId) {
		this.macId = macId;
	}
	/**
	 * @return the passPhrase
	 */
	public String getPassPhrase() {
		return passPhrase;
	}
	/**
	 * @param passPhrase the passPhrase to set
	 */
	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the homeBranch
	 */
	public boolean isHomeBranch() {
		return homeBranch;
	}
	/**
	 * @param homeBranch the homeBranch to set
	 */
	public void setHomeBranch(boolean homeBranch) {
		this.homeBranch = homeBranch;
	}
}
