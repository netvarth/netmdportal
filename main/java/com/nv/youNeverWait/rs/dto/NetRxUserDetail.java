/**
 * NetRxUserDetail.java
 * 
 * @author Asha Chandran
 * 
 * Mar18,2013
 */
package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.pl.entity.NetrxUserTbl;

public class NetRxUserDetail {
	private int globalId;
	private int id;
	private int netRxId;
	private int netRxBranchId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String userType;
	private String userName;
	private String password;
	private String status;
	private String createDateTime;
	private String updateDateTime;
	
	/**
	 * @param globalId
	 * @param id
	 * @param netMdId
	 * @param netMdBranchId
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param mobile
	 * @param address
	 * @param userType
	 * @param userName
	 * @param password
	 * @param status
	 */
	public NetRxUserDetail(NetrxUserTbl userTbl) {
		
		this.globalId = userTbl.getId();
		this.netRxId = userTbl.getNetrxBranchTbl().getNetrxTbl().getId();
		this.netRxBranchId = userTbl.getNetrxBranchTbl().getId();
		this.firstName = userTbl.getFirstName();
		this.lastName = userTbl.getLastName();
		this.email = userTbl.getEmail();
		this.phone = userTbl.getPhone();
		this.mobile = userTbl.getMobile();
		this.address = userTbl.getAddress();
		this.userType = userTbl.getNetrxLoginTbl().getUserType();
		this.userName = userTbl.getNetrxLoginTbl().getUserName();
		this.password = userTbl.getNetrxLoginTbl().getPassword();
		this.status = userTbl.getStatus();
		this.createDateTime = userTbl.getCreateDateTime().toString();
		this.updateDateTime = userTbl.getUpdateDateTime().toString();
	}
	/**
	 * 
	 */
	public NetRxUserDetail() {
		super();
		
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the updateDateTime
	 */
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public int getNetRxId() {
		return netRxId;
	}
	public void setNetRxId(int netRxId) {
		this.netRxId = netRxId;
	}
	public int getNetRxBranchId() {
		return netRxBranchId;
	}
	public void setNetRxBranchId(int netRxBranchId) {
		this.netRxBranchId = netRxBranchId;
	}
	
	
}
