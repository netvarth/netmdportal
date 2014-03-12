/**
 * AppointmentListResponseDTO.java
 */
 package com.nv.youNeverWait.rs.dto;
import java.util.ArrayList;
import java.util.List;

public class AppointmentListResponseDTO {
	private List<AppointmentsDTO> todaysAppointment = new ArrayList<AppointmentsDTO>();
	private List<AppointmentsDTO> currentWeeksAppointment = new ArrayList<AppointmentsDTO>();
	private List<AppointmentsDTO> futureAppointment = new ArrayList<AppointmentsDTO>();
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the todaysAppointment
	 */
	public List<AppointmentsDTO> getTodaysAppointment() {
		return todaysAppointment;
	}
	/**
	 * @param todaysAppointment the todaysAppointment to set
	 */
	public void setTodaysAppointment(List<AppointmentsDTO> todaysAppointment) {
		this.todaysAppointment = todaysAppointment;
	}
	/**
	 * @return the currentWeeksAppointment
	 */
	public List<AppointmentsDTO> getCurrentWeeksAppointment() {
		return currentWeeksAppointment;
	}
	/**
	 * @param currentWeeksAppointment the currentWeeksAppointment to set
	 */
	public void setCurrentWeeksAppointment(
			List<AppointmentsDTO> currentWeeksAppointment) {
		this.currentWeeksAppointment = currentWeeksAppointment;
	}
	/**
	 * @return the futureAppointment
	 */
	public List<AppointmentsDTO> getFutureAppointment() {
		return futureAppointment;
	}
	/**
	 * @param futureAppointment the futureAppointment to set
	 */
	public void setFutureAppointment(List<AppointmentsDTO> futureAppointment) {
		this.futureAppointment = futureAppointment;
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


}
