/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nv
 *
 */
public class RetrievalPatientResponseDTO {
	private List<PatientDetail> retrievePatients = new ArrayList<PatientDetail>();
	private String lastSynctime;
	private ErrorDTO error;
	private boolean success;
	
	public List<PatientDetail> getRetrievePatients() {
		return retrievePatients;
	}
	public void setRetrievePatients(List<PatientDetail> retrievePatients) {
		this.retrievePatients = retrievePatients;
	}
	public String getLastSynctime() {
		return lastSynctime;
	}
	public void setLastSynctime(String lastSynctime) {
		this.lastSynctime = lastSynctime;
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
