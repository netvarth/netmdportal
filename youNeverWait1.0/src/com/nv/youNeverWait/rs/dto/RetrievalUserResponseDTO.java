/**
 * RetrievalUserResponseDTO.java
 *
 * April 26, 2013
 *
 * @author Luciya Jose 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author netvarth
 *
 */
public class RetrievalUserResponseDTO {
	private List<NetMdUserDetail> retrieveUsersList= new ArrayList<NetMdUserDetail>();
	private ErrorDTO error;
	private boolean success;
	
	
/**
	 * @return the retrieveUsersList
	 */
	public List<NetMdUserDetail> getRetrieveUsersList() {
		return retrieveUsersList;
	}
	/**
	 * @param retrieveUsersList the retrieveUsersList to set
	 */
	public void setRetrieveUsersList(List<NetMdUserDetail> retrieveUsersList) {
		this.retrieveUsersList = retrieveUsersList;
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

	// /**
	// * @return the retrieveCreatedUsers
	// */
	// public List<NetMdUserDetail> getRetrieveCreatedUsers() {
	// return retrieveCreatedUsers;
	// }
	// /**
	// * @param retrieveCreatedUsers the retrieveCreatedUsers to set
	// */
	// public void setRetrieveCreatedUsers(List<NetMdUserDetail>
	// retrieveCreatedUsers) {
	// this.retrieveCreatedUsers = retrieveCreatedUsers;
	// }
//	/**
//	 * @return the retrieveUpdatedUsers
//	 */
//	public List<NetMdUserDetail> getRetrieveUpdatedUsers() {
//		return retrieveUpdatedUsers;
//	}
//	/**
//	 * @param retrieveUpdatedUsers the retrieveUpdatedUsers to set
//	 */
//	public void setRetrieveUpdatedUsers(List<NetMdUserDetail> retrieveUpdatedUsers) {
//		this.retrieveUpdatedUsers = retrieveUpdatedUsers;
//	}
	
	/**
	 * 
	 */
	public RetrievalUserResponseDTO() {
		super();
		
	}
	
	
}
