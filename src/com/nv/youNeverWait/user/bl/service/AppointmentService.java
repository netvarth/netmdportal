package com.nv.youNeverWait.user.bl.service;

import java.util.Date;

import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalAppointmentResponseDTO;


public interface AppointmentService {
	public AppointmentListResponseDTO getAppointmentListsForPatient(String patientId);
	public PastAppointmentListResponseDTO getPastAppointmentList(String patientId);
//	public AppointmentResponse createAppointment(Appointment appointment);
//	public AppointmentResponse deleteAppointment(int id);
//	public AppointmentResponse updateAppointment(Appointment appointment);
	public RetrievalAppointmentResponseDTO retrieveAppointmentForSecondary(String lastSyncTime, String passPhrase, int netMdBranchId, Date currentSyncTime);
	/**
	 * @param id
	 * @return
	 */
	public AppointmentResponse deleteAppointmentFromPortal(int id);
	/**
	 * @param id
	 * @return
	 */
	AppointmentResponse deleteAppointmentFromNetMd(int id);
	/**
	 * @param appointment
	 * @return
	 */
	AppointmentResponse updateAppointmentFromPortal(Appointment appointment);
	/**
	 * @param appointment
	 * @return
	 */
	AppointmentResponse updateAppointmentFromNetMd(Appointment appointment);
	/**
	 * @param appointment
	 * @return
	 */
	AppointmentResponse createAppointmentFromPortal(Appointment appointment);
	/**
	 * @param appointment
	 * @return
	 */
	AppointmentResponse createAppointmentFromNetMd(Appointment appointment);
	/**
	 * @param lastSyncTime
	 * @param passPhrase
	 * @param netMdBranchId
	 * @param currentSyncTime
	 * @return
	 */
	public RetrievalAppointmentResponseDTO retrieveAppointmentForPrimary(
			String lastSyncTime, String passPhrase, int netMdBranchId,
			Date currentSyncTime);
	/**
	 * @param filter
	 * @return
	 */
	public PastAppointmentListResponseDTO getPastAppointments(FilterDTO filter);
	
}
