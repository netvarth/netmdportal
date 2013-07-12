/**
 * ScheduleListDTO.java
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
public class ScheduleListDTO {
	private List<ViewScheduleListDTO> scheduleList = new ArrayList<ViewScheduleListDTO>();
	private ErrorDTO error;
	private boolean success;

	/**
	 * 
	 */
	public ScheduleListDTO() {
		super();
	}

	/**
	 * @param scheduleList
	 * @param error
	 * @param success
	 */
	public ScheduleListDTO(List<ViewScheduleListDTO> scheduleList,
			ErrorDTO error, boolean success) {
		super();
		this.scheduleList = scheduleList;
		this.error = error;
		this.success = success;
	}

	/**
	 * @return the scheduleList
	 */
	public List<ViewScheduleListDTO> getScheduleList() {
		return scheduleList;
	}

	/**
	 * @param scheduleList
	 *            the scheduleList to set
	 */
	public void setScheduleList(List<ViewScheduleListDTO> scheduleList) {
		this.scheduleList = scheduleList;
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
