/**
 * DoctorDao.java
 *
 * Dec 20, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.pl.dao;

import java.util.Date;
import java.util.List;

import com.nv.youNeverWait.api.sync.ReferralSyncDTO;
import com.nv.youNeverWait.pl.dao.GenericDao;
import com.nv.youNeverWait.pl.entity.DoctorTbl;
import com.nv.youNeverWait.pl.entity.NetmdLoginTbl;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.DoctorLoginDTO;
import com.nv.youNeverWait.rs.dto.DoctorViewResponseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalDoctorResponseDTO;



public interface DoctorDao  extends GenericDao{
	public ResponseDTO create(DoctorDetail doctor, HeaderDTO header);
	public ResponseDTO update(DoctorDetail doctor,HeaderDTO header);
	public ResponseDTO delete(int globalId);
	public List<DoctorTbl> listDoctors(String clinicId);
	public DoctorViewResponseDTO view(int doctorId);
	public boolean isDoctorLoginExists(String email, String userType);
	public ResponseDTO resetPassword(LoginDTO login);
	public RetrievalDoctorResponseDTO retrieveDoctorList(String lastSyncTime,String passPhrase, int netmdBranchId, Date currentSyncTime);
	public List<DoctorLoginDTO> DoctorPasswordList(String lastSyncTime,String passPhrase, int netmdBranchId, Date currentSyncTime);
	public NetmdLoginTbl DoctorLoginDetails(String email, String userType);
	public int processReferral(ReferralSyncDTO referral);
}
