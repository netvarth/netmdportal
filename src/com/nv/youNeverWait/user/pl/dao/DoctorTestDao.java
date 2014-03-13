/**
 * DoctorTestDao.java
 * March 12, 2014
 */
package com.nv.youNeverWait.user.pl.dao;

import java.util.List;

import com.nv.youNeverWait.pl.entity.DoctorTbl;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.DoctorViewResponseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

/**
 * @author Luciya
 *
 */
public interface DoctorTestDao {
	public ResponseDTO create(DoctorDetail doctor, HeaderDTO header);
	public ResponseDTO update(DoctorDetail doctor,HeaderDTO header);
	public ResponseDTO delete(int globalId);
	public DoctorViewResponseDTO view(int doctorId);
}
