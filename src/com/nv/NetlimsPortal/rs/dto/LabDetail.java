/**
 * LabDetail.java
 *
 * Feb 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.NetlimsPortal.rs.dto;

import com.nv.NetlimsPortal.pl.entity.LabTbl;



public class LabDetail {
	private int globalId;
	private String name;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerEmail;
	private String ownerPhone;
	private String ownerMobile;
	private String ownerAddress;
	private String headOfficeName;
	private String headOfficePhone;
	private String headOfficeMobile;
	private String headOfficeAddress;
	private String status;
	private String headOfficeEmail;

	/**
	 * @param labTbl
	 */
	public LabDetail(LabTbl labTbl) {
		this.globalId = labTbl.getId();
		this.headOfficeAddress = labTbl.getHeadOfficeAddress();
		this.headOfficeEmail = labTbl.getHeadOfficeEmail();
		this.headOfficeMobile= labTbl.getHeadOfficeMobile();
		this.headOfficeName= labTbl.getHeadOfficeName();
		this.headOfficePhone= labTbl.getHeadOfficePhone();
		this.status = labTbl.getStatus();
		this.name = labTbl.getName();
		this.ownerFirstName = labTbl.getOwnerFirstName();
		this.ownerLastName = labTbl.getOwnerLastName();
		this.ownerAddress = labTbl.getOwnerAddress();
		this.ownerEmail = labTbl.getOwnerEmail();
		this.ownerMobile=labTbl.getOwnerMobile();
		this.ownerPhone=labTbl.getOwnerPhone();
	}
	/**
	 * 
	 */
	public LabDetail() {
		// TODO Auto-generated constructor stub
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
	 * @return the ownerPhone
	 */
	public String getOwnerPhone() {
		return ownerPhone;
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


}
