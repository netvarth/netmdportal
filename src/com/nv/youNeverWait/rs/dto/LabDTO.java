/**
 * NetLimsDTO.java
 *
 * Jan 2, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.pl.entity.LabTbl;



/**
 * 
 */
public class LabDTO {
	private int globalId;
	private int id;
	private String name;
	private boolean authToSent;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerEmail;
	private String ownerPhone;
	private String ownerMobile;
	private String ownerAddress;
	private String headOfficeName;
	private String headOfficeEmail;
	private String headOfficePhone;
	private String headOfficeMobile;
	private String headOfficeAddress;
	private String userName;
	private String password;
	private String status;
	private LoginDTO login;
	private String createdDateTime;
	private String updatedDateTime;
	private String labCode;
	private String orderType;


	/**
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the labCode
	 */
	public String getLabCode() {
		return labCode;
	}
	/**
	 * @param labCode the labCode to set
	 */
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public String getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
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
	 * @return the ownerFirstName
	 */
	public String getOwnerFirstName() {
		return ownerFirstName;
	}
	/**
	 * @param ownerFirstName the ownerFirstName to set
	 */
	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	/**
	 * @return the ownerLastName
	 */
	public String getOwnerLastName() {
		return ownerLastName;
	}
	/**
	 * @param ownerLastName the ownerLastName to set
	 */
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
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
	 * @param labTbl
	 */
	public LabDTO(LabTbl labTbl) {
		this.globalId = labTbl.getId();
		this.headOfficeAddress = labTbl.getHeadOfficeAddress();
		this.headOfficeEmail = labTbl.getHeadOfficeEmail();
		this.headOfficeMobile= labTbl.getHeadOfficeMobile();
		this.headOfficeName= labTbl.getHeadOfficeName();
		this.headOfficePhone= labTbl.getHeadOfficePhone();
		this.name = labTbl.getName();
		this.ownerFirstName = labTbl.getOwnerFirstName();
		this.ownerLastName = labTbl.getOwnerLastName();
		this.ownerAddress = labTbl.getOwnerAddress();
		this.ownerEmail = labTbl.getOwnerEmail();
		this.setOwnerMobile(labTbl.getOwnerMobile());
		this.setOwnerPhone(labTbl.getOwnerPhone());
	}
	/**
	 * 
	 */
	public LabDTO() {
		// TODO Auto-generated constructor stub
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
	 * @return the ownerEmail
	 */
	public String getOwnerEmail() {
		return ownerEmail;
	}
	/**
	 * @param ownerEmail the ownerEmail to set
	 */
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	/**
	 * @return the ownerPhone
	 */
	public String getOwnerPhone() {
		return ownerPhone;
	}
	/**
	 * @param ownerPhone the ownerPhone to set
	 */
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	/**
	 * @return the ownerMobile
	 */
	public String getOwnerMobile() {
		return ownerMobile;
	}
	/**
	 * @param ownerMobile the ownerMobile to set
	 */
	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}
	/**
	 * @return the ownerAddress
	 */
	public String getOwnerAddress() {
		return ownerAddress;
	}
	/**
	 * @param ownerAddress the ownerAddress to set
	 */
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	/**
	 * @return the headOfficeName
	 */
	public String getHeadOfficeName() {
		return headOfficeName;
	}
	/**
	 * @param headOfficeName the headOfficeName to set
	 */
	public void setHeadOfficeName(String headOfficeName) {
		this.headOfficeName = headOfficeName;
	}
	/**
	 * @return the headOfficeEmail
	 */
	public String getHeadOfficeEmail() {
		return headOfficeEmail;
	}
	/**
	 * @param headOfficeEmail the headOfficeEmail to set
	 */
	public void setHeadOfficeEmail(String headOfficeEmail) {
		this.headOfficeEmail = headOfficeEmail;
	}
	/**
	 * @return the headOfficePhone
	 */
	public String getHeadOfficePhone() {
		return headOfficePhone;
	}
	/**
	 * @param headOfficePhone the headOfficePhone to set
	 */
	public void setHeadOfficePhone(String headOfficePhone) {
		this.headOfficePhone = headOfficePhone;
	}
	/**
	 * @return the headOfficeMobile
	 */
	public String getHeadOfficeMobile() {
		return headOfficeMobile;
	}
	/**
	 * @param headOfficeMobile the headOfficeMobile to set
	 */
	public void setHeadOfficeMobile(String headOfficeMobile) {
		this.headOfficeMobile = headOfficeMobile;
	}
	/**
	 * @return the headOfficeAddress
	 */
	public String getHeadOfficeAddress() {
		return headOfficeAddress;
	}
	/**
	 * @param headOfficeAddress the headOfficeAddress to set
	 */
	public void setHeadOfficeAddress(String headOfficeAddress) {
		this.headOfficeAddress = headOfficeAddress;
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
	 * @return the authToSent
	 */
	public boolean isAuthToSent() {
		return authToSent;
	}
	/**
	 * @param authToSent the authToSent to set
	 */
	public void setAuthToSent(boolean authToSent) {
		this.authToSent = authToSent;
	}

}
