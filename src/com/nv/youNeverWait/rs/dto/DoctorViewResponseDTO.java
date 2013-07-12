package com.nv.youNeverWait.rs.dto;

public class DoctorViewResponseDTO {
	private DoctorDetail doctorDetail;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the doctorDetail
	 */
	public DoctorDetail getDoctorDetail() {
		return doctorDetail;
	}
	/**
	 * @param doctorDetail the doctorDetail to set
	 */
	public void setDoctorDetail(DoctorDetail doctorDetail) {
		this.doctorDetail = doctorDetail;
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
