/**
 * PatientListResponseDTO.java
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class PatientListResponseDTO {
	private List<PatientDetail> patientList = new ArrayList<PatientDetail>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
	
	public List<PatientDetail> getPatientList() {
		return patientList;
	}
	public void setPatientList(List<PatientDetail> patientList) {
		this.patientList = patientList;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
