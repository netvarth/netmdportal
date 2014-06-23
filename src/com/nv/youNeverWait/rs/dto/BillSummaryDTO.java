/**
 * BillSummaryDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 30-Sep-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.text.SimpleDateFormat;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.NetmdBillTbl;

/**
 *
 *
 * @author Sreeram T G
 */
public class BillSummaryDTO {
private int globalId;
private String uid;
private String payStatus;
private String patientName;
private String patientGlobalId;
private float billAmount;
private float amountPaid;
private String orderDate;
private boolean success;
private ErrorDTO error;
private float amountDue;


/**
 * 
 */
public BillSummaryDTO() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @param globalId
 * @param uid
 * @param payStatus
 * @param patientName
 * @param patientGlobalId
 * @param billAmount
 * @param amountPaid
 * @param orderDate
 * @param success
 * @param error
 */
public BillSummaryDTO(int globalId, String uid, String payStatus,
		String patientName, String patientGlobalId, float billAmount,
		float amountPaid,float amountDue, String orderDate, boolean success, ErrorDTO error) {
	super();
	this.globalId = globalId;
	this.uid = uid;
	this.payStatus = payStatus;
	this.patientName = patientName;
	this.patientGlobalId = patientGlobalId;
	this.billAmount = billAmount;
	this.amountPaid = amountPaid;
	this.amountDue = amountDue;
	this.orderDate = orderDate;
	this.success = success;
	this.error = error;
}
/**
 * @param netmdBillTbl
 */
public BillSummaryDTO(NetmdBillTbl netmdBillTbl) {
	
	SimpleDateFormat sdf= new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
	this.globalId = netmdBillTbl.getId();
	this.uid = netmdBillTbl.getUid();
	this.payStatus = netmdBillTbl.getPayStatus();
	this.patientName = netmdBillTbl.getPatientName();
	this.patientGlobalId = Integer.toString(netmdBillTbl.getNetmdPatientTbl().getId());
	this.billAmount = netmdBillTbl.getBillAmount();
	this.amountPaid = netmdBillTbl.getAmountPaid();
	this.amountDue = netmdBillTbl.getBillAmount()- netmdBillTbl.getAmountPaid();
	this.orderDate = sdf.format(netmdBillTbl.getOrderDate());
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
public float getAmountDue() {
	return amountDue;
}
public void setAmountDue(float amountDue) {
	this.amountDue = amountDue;
}
/**
 * @return the payStatus
 */
public String getPayStatus() {
	return payStatus;
}
/**
 * @param payStatus the payStatus to set
 */
public void setPayStatus(String payStatus) {
	this.payStatus = payStatus;
}
/**
 * @return the patientName
 */
public String getPatientName() {
	return patientName;
}
/**
 * @param patientName the patientName to set
 */
public void setPatientName(String patientName) {
	this.patientName = patientName;
}
/**
 * @return the patientGlobalId
 */
public String getPatientGlobalId() {
	return patientGlobalId;
}
/**
 * @param patientGlobalId the patientGlobalId to set
 */
public void setPatientGlobalId(String patientGlobalId) {
	this.patientGlobalId = patientGlobalId;
}
/**
 * @return the billAmount
 */
public float getBillAmount() {
	return billAmount;
}
/**
 * @param billAmount the billAmount to set
 */
public void setBillAmount(float billAmount) {
	this.billAmount = billAmount;
}
/**
 * @return the amountPaid
 */
public float getAmountPaid() {
	return amountPaid;
}
/**
 * @param amountPaid the amountPaid to set
 */
public void setAmountPaid(float amountPaid) {
	this.amountPaid = amountPaid;
}
/**
 * @return the orderDate
 */
public String getOrderDate() {
	return orderDate;
}
/**
 * @param orderDate the orderDate to set
 */
public void setOrderDate(String orderDate) {
	this.orderDate = orderDate;
}
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

}
