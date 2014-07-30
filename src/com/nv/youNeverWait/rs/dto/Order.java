/**
 * 
 */
package com.nv.youNeverWait.rs.dto;


/**
 * @author Mani E.V
 *
 */
public class Order {
	private int id;
	private String uid;
	private String branchId;
	private String orderStatus;
	private String createdOn;
	private Patient patient;
	private String referralName;
	private String headerData;
	private String branchName;
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the referralName
	 */
	public String getReferralName() {
		return referralName;
	}
	/**
	 * @param referralName the referralName to set
	 */
	public void setReferralName(String referralName) {
		this.referralName = referralName;
	}
	/**
	 * @return the branchId
	 */
	public String getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(String branchId) {
		this.branchId = branchId;
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
	 * @return the headerData
	 */
	public String getHeaderData() {
		return headerData;
	}
	/**
	 * @param headerData the headerData to set
	 */
	public void setHeaderData(String headerData) {
		this.headerData = headerData;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}
