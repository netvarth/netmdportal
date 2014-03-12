/**
 * ViewScheduleListDTO.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 11, 2013
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
public class ViewScheduleListDTO {
	private List<ViewScheduleDTO> schedule = new ArrayList<ViewScheduleDTO>();
	private List<AppointmentDetailsDTO> appointment = new ArrayList<AppointmentDetailsDTO>();
	private ErrorDTO error;
	private boolean success;

	/**
	 * 
	 */
	public ViewScheduleListDTO() {
		super();
	}

	/**
	 * @param schedule
	 * @param error
	 * @param success
	 */
	public ViewScheduleListDTO(List<ViewScheduleDTO> schedule, ErrorDTO error,
			boolean success) {
		super();
		this.schedule = schedule;
		this.error = error;
		this.success = success;
	}

	/**
	 * @return the schedule
	 */
	public List<ViewScheduleDTO> getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule
	 *            the schedule to set
	 */
	public void setSchedule(List<ViewScheduleDTO> schedule) {
		this.schedule = schedule;
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

	public List<AppointmentDetailsDTO> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<AppointmentDetailsDTO> appointment) {
		this.appointment = appointment;
	}
}
