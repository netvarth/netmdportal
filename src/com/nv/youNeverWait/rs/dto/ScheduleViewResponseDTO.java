/**
 * ScheduleViewResponseDTO.java
 * 
 * Mar 15, 2013
 * Luciya
 */
package com.nv.youNeverWait.rs.dto;

public class ScheduleViewResponseDTO {
	private ScheduleDetail scheduleDetail;
	private DoctorDetail doctor;
	private ErrorDTO error;
	private boolean success;
		
	/**
	 * @return the doctor
	 */
	public DoctorDetail getDoctor() {
		return doctor;
	}
	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(DoctorDetail doctor) {
		this.doctor = doctor;
	}
	/**
	 * @return the scheduleDetail
	 */
	public ScheduleDetail getScheduleDetail() {
		return scheduleDetail;
	}
	/**
	 * @param scheduleDetail the scheduleDetail to set
	 */
	public void setScheduleDetail(ScheduleDetail scheduleDetail) {
		this.scheduleDetail = scheduleDetail;
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
