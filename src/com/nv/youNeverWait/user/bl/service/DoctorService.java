/**
 * DoctorService.java
 *
 * Dec 19, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.bl.service;


import java.util.Date;
import java.util.List;

import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.DoctorLoginDTO;
import com.nv.youNeverWait.rs.dto.DoctorViewResponseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.DoctorListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalDoctorResponseDTO;



public interface DoctorService {

	public ResponseDTO create(DoctorDetail doctor,HeaderDTO header);
	public ResponseDTO update(DoctorDetail doctor,HeaderDTO header);
	public ResponseDTO delete(int globalId);
	public DoctorListResponseDTO listDoctors(String clinicId);
	public DoctorViewResponseDTO view(int doctorId);
	public ResponseDTO resetPassword(LoginDTO login);
	public RetrievalDoctorResponseDTO retrieveDoctorList(String lastSyncTime,String passPhrase, int netmdBranchId, Date currentSyncTime);
	public List<DoctorLoginDTO> DoctorPasswordList(String lastSyncTime,String passPhrase, int netmdBranchId, Date currentSyncTime);
}
