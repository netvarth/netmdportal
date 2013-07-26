/**
 * 
 */
package com.nv.youNeverWait.user.pl.dao;

import java.util.Date;
import java.util.List;

import com.nv.youNeverWait.pl.entity.DoctorTbl;
import com.nv.youNeverWait.pl.entity.PatientTbl;
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.RetrievalPatientResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;

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
	public List<DoctorTbl> listDoctors(String clinicId, String patientId);
	public List<PatientTbl> listOfPatientsOnLogin(String patientEmailId);
	public ResponseDTO changePassword(PasswordDTO passwords);
	public ResponseDTO createPassword(CreatePasswordDTO passwords);
	public String getBranch(int branchId);
	public RetrievalPatientResponseDTO retrievePatientsForNetMd(String lastSyncTime, String passPhrase, int netMdBranchId, Date currentSyncTime);
	public UserCredentials getUserCredentials(LoginDTO login);
	public ResponseDTO resetPassword(LoginDTO login);
	public ResultDTO patientTestResult(PatientOrderDTO patient);
}
