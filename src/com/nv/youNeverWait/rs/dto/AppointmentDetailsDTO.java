/**
 * AppointmentDetailsDTO.java
 */
package com.nv.youNeverWait.rs.dto;

import java.text.SimpleDateFormat;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;

/**
 * @author nv
 *
 */
public class AppointmentDetailsDTO {
	private int id;
	private int globalId;
	private int patientId;
	private int doctorId;
	private int scheduleId;
	private String startDate;
	private String startTime;
	private String patientName;
	private String emailId;
	
	
	public AppointmentDetailsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppointmentDetailsDTO(int id, int patientId, int doctorId,
			int scheduleId, String startDate, String startTime,
			String patientName,String emailId) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.scheduleId = scheduleId;
		this.startDate = startDate;
		this.startTime = startTime;
		this.patientName = patientName;
		this.emailId=emailId;
	}
	public AppointmentDetailsDTO(PatientAppointmentTbl patientAppointmentTblObj) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		SimpleDateFormat sdf2 = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		this.id = patientAppointmentTblObj.getId();
		this.patientId = patientAppointmentTblObj.getPatientTbl().getId();
		this.doctorId = patientAppointmentTblObj.getDoctorTbl().getId();
		this.scheduleId = patientAppointmentTblObj.getDoctorScheduleTbl().getId();
		this.startDate = sdf.format(patientAppointmentTblObj.getAppointmentDate());
		this.startTime = sdf2.format(patientAppointmentTblObj.getStartingTime());
		this.patientName = patientAppointmentTblObj.getPatientTbl().getFirstName()+" "+patientAppointmentTblObj.getPatientTbl().getLastName();
		this.emailId=patientAppointmentTblObj.getPatientTbl().getEmail();
	}
	public int getGlobalId() {
		return globalId;
	}
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
