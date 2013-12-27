/**
 * AppointmentsDTO.java
 */
package com.nv.youNeverWait.rs.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;

public class AppointmentsDTO {
	private int id;
	private String date;
	private String startingTime;
	private String doctorFirstName;
	private String doctorLastName;
	private String doctorphone;
	private String doctormobile;
	private String doctoraddress;
	private String doctorSpecialisation;
	private String doctorDesignation;
	private String appointmentStatus;

	public AppointmentsDTO(PatientAppointmentTbl patientAppointment) {
		DateFormat df = new SimpleDateFormat(
				Constants.NEWDATE_FORMAT_FOR_APPOINTMENT);
		DateFormat dft = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		this.id = patientAppointment.getId();
		this.date = df.format(patientAppointment.getAppointmentDate());
		this.startingTime = dft.format(patientAppointment.getStartingTime());
		this.appointmentStatus = patientAppointment.getAppointmentStatus();
		this.doctorFirstName = patientAppointment.getDoctorTbl().getFirstName();
		this.doctorLastName = patientAppointment.getDoctorTbl().getLastName();
		this.doctorphone = patientAppointment.getDoctorTbl().getPhone();
		this.doctormobile = patientAppointment.getDoctorTbl().getMobile();
		this.doctoraddress = patientAppointment.getDoctorTbl().getAddress();
		this.doctorSpecialisation = patientAppointment.getDoctorTbl().getSpecialization();
		this.doctorDesignation = patientAppointment.getDoctorTbl().getDesignation();
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the doctorFirstName
	 */
	public String getDoctorFirstName() {
		return doctorFirstName;
	}
	/**
	 * @param doctorFirstName the doctorFirstName to set
	 */
	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}
	/**
	 * @return the doctorLastName
	 */
	public String getDoctorLastName() {
		return doctorLastName;
	}
	/**
	 * @param doctorLastName the doctorLastName to set
	 */
	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}
	/**
	 * @return the doctorphone
	 */
	public String getDoctorphone() {
		return doctorphone;
	}
	/**
	 * @param doctorphone the doctorphone to set
	 */
	public void setDoctorphone(String doctorphone) {
		this.doctorphone = doctorphone;
	}
	/**
	 * @return the doctormobile
	 */
	public String getDoctormobile() {
		return doctormobile;
	}
	/**
	 * @param doctormobile the doctormobile to set
	 */
	public void setDoctormobile(String doctormobile) {
		this.doctormobile = doctormobile;
	}
	/**
	 * @return the doctoraddress
	 */
	public String getDoctoraddress() {
		return doctoraddress;
	}
	/**
	 * @param doctoraddress the doctoraddress to set
	 */
	public void setDoctoraddress(String doctoraddress) {
		this.doctoraddress = doctoraddress;
	}
	/**
	 * @return the doctorSpecialisation
	 */
	public String getDoctorSpecialisation() {
		return doctorSpecialisation;
	}
	/**
	 * @param doctorSpecialisation the doctorSpecialisation to set
	 */
	public void setDoctorSpecialisation(String doctorSpecialisation) {
		this.doctorSpecialisation = doctorSpecialisation;
	}
	/**
	 * @return the doctorDesignation
	 */
	public String getDoctorDesignation() {
		return doctorDesignation;
	}
	/**
	 * @param doctorDesignation the doctorDesignation to set
	 */
	public void setDoctorDesignation(String doctorDesignation) {
		this.doctorDesignation = doctorDesignation;
	}
	/**
	 * @return the appointmentStatus
	 */
	public String getAppointmentStatus() {
		return appointmentStatus;
	}
	/**
	 * @param appointmentStatus the appointmentStatus to set
	 */
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartingTime() {
		return startingTime;
	}
	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}
	
}
