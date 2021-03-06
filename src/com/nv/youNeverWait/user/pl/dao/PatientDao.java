/**
 * 
 */
package com.nv.youNeverWait.user.pl.dao;

import java.util.Date;
import java.util.List;

import com.nv.youNeverWait.pl.entity.NetmdDoctorTbl;
import com.nv.youNeverWait.pl.entity.NetmdPatientTbl;
import com.nv.youNeverWait.rs.dto.CaseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.MedicalRecordDTO;
import com.nv.youNeverWait.rs.dto.Patient;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.RetrievalPatientResponseDTO;

/**
 * @author Luciya Jose
 *
 */
public interface PatientDao {
	
	public ResponseDTO createPatient(PatientDetail patient, HeaderDTO header);
	public ResponseDTO updatePatient(PatientDetail patient, HeaderDTO header);
	public PatientDetail viewPatient(int globalId);
	//public NetmdPatientBranchTbl deleteBranchByPatientId( int patientId);
	public ResponseDTO deletePatient(int globalId);
	//public PatientDetail listNetMdBranches(String userName);
	public List<NetmdDoctorTbl> listDoctors(String clinicId, String patientId);
	public List<NetmdPatientTbl> listOfPatientsOnLogin(String patientEmailId);
	public String getBranch(int branchId);
	public RetrievalPatientResponseDTO retrievePatientsForNetMd(String lastSyncTime, String passPhrase, int netMdBranchId, Date currentSyncTime);
	public ResultDTO patientTestResult(PatientOrderDTO patient);
	public ResponseDTO createCase(CaseDTO newPatientCase, HeaderDTO header);
	public ResponseDTO updateCase(CaseDTO updatedPatientCase,
			HeaderDTO header);
	public boolean isEmailExists(int id, String email);
	public String getDepartmentNameById(int departmentId);
	public ResponseDTO deleteCase(CaseDTO deletePatientCase, HeaderDTO header);
	public ResponseDTO createMedicalRecord(MedicalRecordDTO newPatientMedicalRecord, HeaderDTO header);
	public ResponseDTO updateMedicalRecord(MedicalRecordDTO updatedMedicalRecord, HeaderDTO header);
	public ResponseDTO deleteMedicalRecord(MedicalRecordDTO deleteMedicalRecord, HeaderDTO header);
	
	public int getNetlimsPatient(Patient patient,int branchId);
	public int getPatientId(Patient patient);
	/**
	 * create a patient using sync data from netlims
	 * @param patient
	 * @param login 
	 * @return patient id
	 */
	public int create(Patient patient, LoginDTO login);
	public LoginDTO setLoginInfo(LoginDTO login);
	
	
	
	
}
