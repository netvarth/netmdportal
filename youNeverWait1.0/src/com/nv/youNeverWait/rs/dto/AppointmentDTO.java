/**
 * AppointmentDTO.java
 * It is used to give appointment details to NetMD. 
 * Appointments from youneverwait is send to netMDs as list of AppointmentDTO.
 * 
 * @Author Ricky
 *
 * Version 1.0 Dec 27, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;

/**
 * 
 */
public class AppointmentDTO {
	private int id;
	private int globalId;
	private int patientId;
	private int doctorId;
	private int scheduleId;
	private String startDate;
	private String startTime;
	private String createdDateTime;
	private String updatedDateTime;
	private String patientName;
	private String appointmentStatus;
	private String status;

	/**
	 * 
	 */
	public AppointmentDTO() {
		super();
	}

	/**
	 * @param id
	 * @param patientId
	 * @param doctorId
	 * @param scheduleId
	 * @param startDate
	 * @param startTime
	 * @param patientName
	 */
	public AppointmentDTO(int id, int globalId, int patientId, int doctorId, int scheduleId,
			Date startDate, Date startTime, Date createdDateTime, Date updatedDateTime, String patientName, String appointmentStatus, String status) {
		super();
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		SimpleDateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		SimpleDateFormat df2 = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		
		this.id = id;
		this.globalId = globalId;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.scheduleId = scheduleId;
		this.startDate = df.format(startDate);
		this.startTime = df1.format(startTime);
		this.createdDateTime = df2.format(createdDateTime);
		this.updatedDateTime = df2.format(updatedDateTime);
		this.patientName = patientName;
		this.appointmentStatus = appointmentStatus;
		this.status = status;
	}

	/**
	 * @param appointmnets
	 */
	public AppointmentDTO(PatientAppointmentTbl appointmnets) {
		SimpleDateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		SimpleDateFormat df1 = new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		SimpleDateFormat df2 = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		
		this.globalId = appointmnets.getId();
		this.patientId = appointmnets.getPatientTbl().getId();
		this.doctorId = appointmnets.getDoctorTbl().getId();
		this.scheduleId = appointmnets.getDoctorScheduleTbl().getId();
		this.startDate = df.format(appointmnets.getDate());
		this.startTime = df1.format(appointmnets.getStartingTime());
		this.createdDateTime = df2.format(appointmnets.getCreateDateTime());
		this.updatedDateTime = df2.format(appointmnets.getUpdateDateTime());
		this.patientName = appointmnets.getPatientTbl().getFirstName();
		this.appointmentStatus = appointmnets.getAppointmentStatus();
		this.status = appointmnets.getStatus();
	}

	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId
	 *            the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId
	 *            the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the scheduleId
	 */
	public int getScheduleId() {
		return scheduleId;
	}

	/**
	 * @param scheduleId
	 *            the scheduleId to set
	 */
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName
	 *            the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the globalId
	 */
	public int getGlobalId() {
		return globalId;
	}

	/**
	 * @param globalId
	 *            the globalId to set
	 */
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
