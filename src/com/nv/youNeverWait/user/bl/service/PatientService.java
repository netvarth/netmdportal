/**
 * 
 */
package com.nv.youNeverWait.user.bl.service;


import java.util.Date;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
import com.nv.youNeverWait.rs.dto.DoctorListResponseDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalPatientResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;



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
	public AppointmentResponse createAppointment(Appointment appointment);
	public AppointmentResponse deleteAppointment(int id);
	public ResponseDTO changePassword(PasswordDTO passwords);
	public ResponseDTO createPassword(CreatePasswordDTO passwords);
	public AppointmentResponse updateAppointment(Appointment appointment);
	public RetrievalPatientResponseDTO retrievePatientsForNetMd(String lastSyncTime, String passPhrase, int netMdBranchId, Date currentSyncTime);
	public ResponseDTO forgotPassword(LoginDTO login);
	public ResponseDTO resetPassword(LoginDTO login) ;
	public ResultListResponseDTO getresultList(FilterDTO filter);
	public ResultDTO patientTestResult(PatientOrderDTO patient);
	
	}
