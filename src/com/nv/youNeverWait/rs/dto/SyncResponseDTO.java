/**
 * SyncResponseDTO.java
 * 
 * @Author Luciya Jose
 *
 * April 05, 2013
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luciya Jose
 *
 */
public class SyncResponseDTO {
	public HeaderResponseDTO headerResponse ;
	private List<DoctorResponse> doctorResponse = new ArrayList<DoctorResponse>();
	private List<DoctorLoginDTO> doctorLogin = new ArrayList<DoctorLoginDTO>();
	private List<ScheduleResponse> scheduleResponse = new ArrayList<ScheduleResponse>();
	private List<PatientResponse> patientResponse = new ArrayList<PatientResponse>();
	private List<UserResponse> userResponse = new ArrayList<UserResponse>();
	private List<AppointmentResponse> appointmentResponse = new ArrayList<AppointmentResponse>();
	private RetrievalDoctorResponseDTO retrievalDoctorList= new RetrievalDoctorResponseDTO();
	private RetrievalUserResponseDTO retrievalUsersList= new RetrievalUserResponseDTO();
	private RetrievalAppointmentResponseDTO retrievalAppointmentList = new RetrievalAppointmentResponseDTO();
	private RetrievalPatientResponseDTO retrievalPatientDTO = new RetrievalPatientResponseDTO();
	private RetrievalScheduleResponseDTO retrievalScheduleList= new RetrievalScheduleResponseDTO();
	private List<RetrieveResultsResponseDTO> retrieveResults= new ArrayList<RetrieveResultsResponseDTO>();
	private String lastSynctime;
	private ErrorDTO error;
	private boolean success;
	
	
	
	/**
	 * @return the retrieveResults
	 */
	public List<RetrieveResultsResponseDTO> getRetrieveResults() {
		return retrieveResults;
	}
	/**
	 * @param retrieveResults the retrieveResults to set
	 */
	public void setRetrieveResults(List<RetrieveResultsResponseDTO> retrieveResults) {
		this.retrieveResults = retrieveResults;
	}
	/**
	 * @return the doctorLogin
	 */
	public List<DoctorLoginDTO> getDoctorLogin() {
		return doctorLogin;
	}
	/**
	 * @param doctorLogin the doctorLogin to set
	 */
	public void setDoctorLogin(List<DoctorLoginDTO> doctorLogin) {
		this.doctorLogin = doctorLogin;
	}
	/**
	 * @return the retrievalScheduleList
	 */
	public RetrievalScheduleResponseDTO getRetrievalScheduleList() {
		return retrievalScheduleList;
	}
	/**
	 * @param retrievalScheduleList the retrievalScheduleList to set
	 */
	public void setRetrievalScheduleList(
			RetrievalScheduleResponseDTO retrievalScheduleList) {
		this.retrievalScheduleList = retrievalScheduleList;
	}
	/**
	 * @return the retrievalUsersList
	 */
	public RetrievalUserResponseDTO getRetrievalUsersList() {
		return retrievalUsersList;
	}
	/**
	 * @param retrievalUsersList the retrievalUsersList to set
	 */
	public void setRetrievalUsersList(RetrievalUserResponseDTO retrievalUsersList) {
		this.retrievalUsersList = retrievalUsersList;
	}
	/**
	 * @return the retrievalDoctorList
	 */
	public RetrievalDoctorResponseDTO getRetrievalDoctorList() {
		return retrievalDoctorList;
	}
	/**
	 * @param retrievalDoctorList the retrievalDoctorList to set
	 */
	public void setRetrievalDoctorList(
			RetrievalDoctorResponseDTO retrievalDoctorList) {
		this.retrievalDoctorList = retrievalDoctorList;
	}
	/**
	 * @return the lastSynctime
	 */
	public String getLastSynctime() {
		return lastSynctime;
	}
	/**
	 * @param lastSynctime the lastSynctime to set
	 */
	public void setLastSynctime(String lastSynctime) {
		this.lastSynctime = lastSynctime;
	}
	/**
	 * @return the scheduleResponse
	 */
	public List<ScheduleResponse> getScheduleResponse() {
		return scheduleResponse;
	}
	/**
	 * @param scheduleResponse the scheduleResponse to set
	 */
	public void setScheduleResponse(List<ScheduleResponse> scheduleResponse) {
		this.scheduleResponse = scheduleResponse;
	}
	/**
	 * @return the headerResponse
	 */
	public HeaderResponseDTO getHeaderResponse() {
		return headerResponse;
	}
	/**
	 * @param headerResponse the headerResponse to set
	 */
	public void setHeaderResponse(HeaderResponseDTO headerResponse) {
		this.headerResponse = headerResponse;
	}
	/**
	 * @return the userResponse
	 */
	public List<UserResponse> getUserResponse() {
		return userResponse;
	}
	/**
	 * @param userResponse the userResponse to set
	 */
	public void setUserResponse(List<UserResponse> userResponse) {
		this.userResponse = userResponse;
	}
	/**
	 * @return the doctorResponse
	 */
	public List<DoctorResponse> getDoctorResponse() {
		return doctorResponse;
	}
	/**
	 * @param doctorResponse the doctorResponse to set
	 */
	public void setDoctorResponse(List<DoctorResponse> doctorResponse) {
		this.doctorResponse = doctorResponse;
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
	
	/**
	 * @return the patientResponse
	 */
	public List<PatientResponse> getPatientResponse() {
		return patientResponse;
	}
	/**
	 * @param patientResponse the patientResponse to set
	 */
	public void setPatientResponse(List<PatientResponse> patientResponse) {
		this.patientResponse = patientResponse;
	}
	/**
	 * 
	 */
	public SyncResponseDTO() {
		
	}
	/**
	 * @param doctorResponse
	 * @param patientResponse
	 * @param userResponse
	 * @param error
	 * @param success
	 */
	public SyncResponseDTO(List<DoctorResponse> doctorResponse,
			List<PatientResponse> patientResponse,
			List<UserResponse> userResponse, ErrorDTO error, boolean success) {
		super();
		this.doctorResponse = doctorResponse;
		this.patientResponse = patientResponse;
		this.userResponse = userResponse;
		this.error = error;
		this.success = success;
	}
	public List<AppointmentResponse> getAppointmentResponse() {
		return appointmentResponse;
	}
	public void setAppointmentResponse(List<AppointmentResponse> appointmentResponse) {
		this.appointmentResponse = appointmentResponse;
	}
	public RetrievalAppointmentResponseDTO getRetrievalAppointmentList() {
		return retrievalAppointmentList;
	}
	public void setRetrievalAppointmentList(
			RetrievalAppointmentResponseDTO retrievalAppointmentList) {
		this.retrievalAppointmentList = retrievalAppointmentList;
	}
	public RetrievalPatientResponseDTO getRetrievalPatientDTO() {
		return retrievalPatientDTO;
	}
	public void setRetrievalPatientDTO(
			RetrievalPatientResponseDTO retrievalPatientDTO) {
		this.retrievalPatientDTO = retrievalPatientDTO;
	}
	

}
