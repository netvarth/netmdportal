/**
 * PatientService.java
 */
package com.nv.youNeverWait.user.bl.service;


import java.util.Date;

import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.CaseDTO;
import com.nv.youNeverWait.rs.dto.DoctorListResponseDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.MedicalRecordDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.Patient;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalPatientResponseDTO;




/**
 * @author Luciya Jose
 *
 */
public interface PatientService {
	public ResponseDTO createPatient(PatientDetail patient, HeaderDTO header);
	public ResponseDTO updatePatient(PatientDetail patient,HeaderDTO header);
	public PatientDetail viewPatient(int globalId);
	public ResponseDTO deletePatient(int globalId);
	public NetMdBranchListResponseDTO getBranchList(FilterDTO filterDTO);
	public DoctorListResponseDTO listDoctors(String clinicId);
	public AppointmentListResponseDTO listAppointmentsForPatient(String patientId);
	public PatientListResponseDTO patientListOnLogin(String patientEmailId);
	public PastAppointmentListResponseDTO getPastAppointmentList(String patientId);
	//public AppointmentResponse createAppointment(Appointment appointment);
	//public AppointmentResponse deleteAppointment(int id);

	//public AppointmentResponse updateAppointment(Appointment appointment);
	public RetrievalPatientResponseDTO retrievePatientsForNetMd(String lastSyncTime, String passPhrase, int netMdBranchId, Date currentSyncTime);
	public ResultListResponseDTO getresultList(FilterDTO filter);
	public ResultDTO patientTestResult(PatientOrderDTO patient);
	public AppointmentResponse deleteAppointmentFromPortal(int id);
	public AppointmentResponse createAppointmentFromNetMd(
			Appointment appointment);
	public AppointmentResponse createAppointmentFromPortal(
			Appointment appointment);
	public AppointmentResponse updateAppointmentFromNetMd(
			Appointment appointment);
	public AppointmentResponse updateAppointmentFromPortal(
			Appointment appointment);
	public AppointmentResponse deleteAppointmentFromNetmd(int id);
	public PastAppointmentListResponseDTO getPastAppointments(FilterDTO filter);
	public ResponseDTO createCase(CaseDTO newPatientCase, HeaderDTO header);
	public ResponseDTO updateCase(CaseDTO updatedCase, HeaderDTO header);
	public ResponseDTO deleteCase(CaseDTO deleteCaseList, HeaderDTO header);
	public ResponseDTO createMedicalRecord(MedicalRecordDTO newPatientMedicalRecord, HeaderDTO header);
	public ResponseDTO updatePatientMedicalRecord(MedicalRecordDTO updatedMedicalRecord, HeaderDTO header);
	public ResponseDTO deletePatientMedicalRecord(MedicalRecordDTO deleteMedicalRecord, HeaderDTO header);
	public int getPatient(Patient patient, String source_branch);
}
