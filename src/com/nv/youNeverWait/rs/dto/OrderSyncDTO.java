/**
 * OrderSyncDTO.java
 * @author Mani E.V 
 *
 * Version 1.0 06-Jun-2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.util.Date;

/**
 *
 *
 * @author Mani E.V
 */
public class OrderSyncDTO {
	private String uid;
	private PatientDTO patient;
	private int referralGlobalId;
	private int agentGlobalId;
	private int facilityGlobalId;
	private String paymentStatus;
	private Date orderDate;
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
	 * @return the patient
	 */
	public PatientDTO getPatient() {
		return patient;
	}
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
	/**
	 * @return the referralGlobalId
	 */
	public int getReferralGlobalId() {
		return referralGlobalId;
	}
	/**
	 * @param referralGlobalId the referralGlobalId to set
	 */
	public void setReferralGlobalId(int referralGlobalId) {
		this.referralGlobalId = referralGlobalId;
	}
	/**
	 * @return the agentGlobalId
	 */
	public int getAgentGlobalId() {
		return agentGlobalId;
	}
	/**
	 * @param agentGlobalId the agentGlobalId to set
	 */
	public void setAgentGlobalId(int agentGlobalId) {
		this.agentGlobalId = agentGlobalId;
	}
	/**
	 * @return the facilityGlobalId
	 */
	public int getFacilityGlobalId() {
		return facilityGlobalId;
	}
	/**
	 * @param facilityGlobalId the facilityGlobalId to set
	 */
	public void setFacilityGlobalId(int facilityGlobalId) {
		this.facilityGlobalId = facilityGlobalId;
	}
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @param date the orderDate to set
	 */
	public void setOrderDate(Date date) {
		this.orderDate = date;
	}
	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
}
