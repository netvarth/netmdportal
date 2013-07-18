package com.nv.youNeverWait.user.bl.service;

import java.util.Date;

import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalAppointmentResponseDTO;


public interface AppointmentService {
	public AppointmentListResponseDTO getAppointmentListsForPatient(String patientId);
	public PastAppointmentListResponseDTO getPastAppointmentList(String patientId);
	public AppointmentResponse createAppointment(Appointment appointment);
	public AppointmentResponse deleteAppointment(int id);
	public AppointmentResponse updateAppointment(Appointment appointment);
	public RetrievalAppointmentResponseDTO retrieveAppointmentForNetMd(String lastSyncTime, String passPhrase, int netMdBranchId, Date currentSyncTime);
}
