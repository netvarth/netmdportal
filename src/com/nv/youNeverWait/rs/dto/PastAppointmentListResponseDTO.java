/**
 * PastAppointmentListResponseDTO.java
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nv
 *
 */
public class PastAppointmentListResponseDTO {
	private List<AppointmentsDTO> pastAppointments= new ArrayList<AppointmentsDTO>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
	public List<AppointmentsDTO> getPastAppointments() {
		return pastAppointments;
	}
	public void setPastAppointments(List<AppointmentsDTO> pastAppointments) {
		this.pastAppointments = pastAppointments;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
