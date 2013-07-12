/**
 * RetrievalDoctorResponseDTO.java
 * 
 * @Author Luciya Jose
 *
 * April 25, 2013
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class RetrievalDoctorResponseDTO {
	private List<DoctorDetail> retrieveDoctorsList = new ArrayList<DoctorDetail>();
//	private List<DoctorDetail> retrieveUpdatedDoctors = new ArrayList<DoctorDetail>();
//	private List<DoctorDetail> retrieveDeletedDoctors = new ArrayList<DoctorDetail>();
	private ErrorDTO error;
	private boolean success;
//	/**
//	 * @return the retrieveCreatedDoctors
//	 */
//	public List<DoctorDetail> getRetrieveCreatedDoctors() {
//		return retrieveCreatedDoctors;
//	}
//	/**
//	 * @param retrieveCreatedDoctors the retrieveCreatedDoctors to set
//	 */
//	public void setRetrieveCreatedDoctors(List<DoctorDetail> retrieveCreatedDoctors) {
//		this.retrieveCreatedDoctors = retrieveCreatedDoctors;
//	}
//	
//	/**
//	 * @return the retrieveUpdatedDoctors
//	 */
//	public List<DoctorDetail> getRetrieveUpdatedDoctors() {
//		return retrieveUpdatedDoctors;
//	}
//	/**
//	 * @param retrieveUpdatedDoctors the retrieveUpdatedDoctors to set
//	 */
//	public void setRetrieveUpdatedDoctors(List<DoctorDetail> retrieveUpdatedDoctors) {
//		this.retrieveUpdatedDoctors = retrieveUpdatedDoctors;
//	}
//	/**
//	 * @return the retrieveDeletedDoctors
//	 */
//	public List<DoctorDetail> getRetrieveDeletedDoctors() {
//		return retrieveDeletedDoctors;
//	}
//	/**
//	 * @param retrieveDeletedDoctors the retrieveDeletedDoctors to set
//	 */
//	public void setRetrieveDeletedDoctors(List<DoctorDetail> retrieveDeletedDoctors) {
//		this.retrieveDeletedDoctors = retrieveDeletedDoctors;
//	}

	
	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	/**
	 * @return the retrieveDoctorsList
	 */
	public List<DoctorDetail> getRetrieveDoctorsList() {
		return retrieveDoctorsList;
	}
	/**
	 * @param retrieveDoctorsList the retrieveDoctorsList to set
	 */
	public void setRetrieveDoctorsList(List<DoctorDetail> retrieveDoctorsList) {
		this.retrieveDoctorsList = retrieveDoctorsList;
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
