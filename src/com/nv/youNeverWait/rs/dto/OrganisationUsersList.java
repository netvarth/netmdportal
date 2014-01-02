/**
 * OrganisationUsersList.java
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
public class OrganisationUsersList {
	private List<OrganisationUserDetail> organisationUsers = new ArrayList<OrganisationUserDetail>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the organisationUsers
	 */
	public List<OrganisationUserDetail> getOrganisationUsers() {
		return organisationUsers;
	}
	/**
	 * @param organisationUsers the organisationUsers to set
	 */
	public void setOrganisationUsers(List<OrganisationUserDetail> organisationUsers) {
		this.organisationUsers = organisationUsers;
	}
	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
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
	
}
