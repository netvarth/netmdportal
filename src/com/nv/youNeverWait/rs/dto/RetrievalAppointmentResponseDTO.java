/**
 * RetrievalAppointmentResponseDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Apr 25, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class RetrievalAppointmentResponseDTO {
	private List<AppointmentDTO> retrieveAppointments = new ArrayList<AppointmentDTO>();
	private String lastSynctime;
	private ErrorDTO error;
	private boolean success;

	public List<AppointmentDTO> getRetrieveAppointments() {
		return retrieveAppointments;
	}

	public void setRetrieveAppointments(
			List<AppointmentDTO> retrieveAppointments) {
		this.retrieveAppointments = retrieveAppointments;
	}

	/**
	 * @return the lastSynctime
	 */
	public String getLastSynctime() {
		return lastSynctime;
	}

	/**
	 * @param lastSynctime
	 *            the lastSynctime to set
	 */
	public void setLastSynctime(String lastSynctime) {
		this.lastSynctime = lastSynctime;
	}

	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
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
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
