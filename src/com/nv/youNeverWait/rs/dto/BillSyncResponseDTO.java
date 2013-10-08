/**
 * BillSyncResponseDTO.java
 * @author Sreeram T G 
 *
 * Version 1.0 30-Sep-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

/**
 *
 *
 * @author Sreeram T G
 */
public class BillSyncResponseDTO {
private int globalId;
private String uid;
private boolean success;
private ErrorDTO error;
private String actionName;
private String createDateTime;
private String updateDateTime;

/**
 * 
 */
public BillSyncResponseDTO() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @param globalId
 * @param uid
 * @param success
 * @param error
 */
public BillSyncResponseDTO(int globalId, String uid, boolean success,
		ErrorDTO error) {
	super();
	this.globalId = globalId;
	this.uid = uid;
	this.success = success;
	this.error = error;
}


/**
 * @return the actionName
 */
public String getActionName() {
	return actionName;
}
/**
 * @param actionName the actionName to set
 */
public void setActionName(String actionName) {
	this.actionName = actionName;
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
