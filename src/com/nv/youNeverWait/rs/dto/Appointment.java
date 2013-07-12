/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author nv
 *
 */
public class Appointment {
	private HeaderDTO header;
	private AppointmentDetailsDTO appointmentDetails;
	
	
	public HeaderDTO getHeader() {
		return header;
	}
	public void setHeader(HeaderDTO header) {
		this.header = header;
	}
	public AppointmentDetailsDTO getAppointmentDetails() {
		return appointmentDetails;
	}
	public void setAppointmentDetails(AppointmentDetailsDTO appointmentDetails) {
		this.appointmentDetails = appointmentDetails;
	}
	
}
