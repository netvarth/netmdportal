/**
 * ViewOrganisationUser.java
 * @author netvarth
 *
 * Version 1.0 Jan 27, 2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Luciya Jose
 */
public class ViewOrganisationUser {
 private OrganisationUserDetail userDetails= new OrganisationUserDetail();
 private ErrorDTO error;
 private boolean success;

 
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
 * @return the userDetails
 */
public OrganisationUserDetail getUserDetails() {
	return userDetails;
}
/**
 * @param userDetails the userDetails to set
 */
public void setUserDetails(OrganisationUserDetail userDetails) {
	this.userDetails = userDetails;
}
 
}
