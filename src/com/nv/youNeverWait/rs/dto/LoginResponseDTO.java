/**
 * LoginResponseDTO.java
 *
 * Dec 3, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

import com.nv.youNeverWait.rs.dto.ErrorDTO;

public class LoginResponseDTO {
	private List<PatientDetail> patientList = new ArrayList<PatientDetail>();
	private boolean success;
	private ErrorDTO error;
	
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
	public List<PatientDetail> getPatientList() {
		return patientList;
	}
	public void setPatientList(List<PatientDetail> patientList) {
		this.patientList = patientList;
	}
	
}
