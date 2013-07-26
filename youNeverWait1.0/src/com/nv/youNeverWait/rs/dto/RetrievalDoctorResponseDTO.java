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
	private ErrorDTO error;
	private boolean success;

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
