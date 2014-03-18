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
import com.nv.youNeverWait.rs.dto.NetMdDTO;
import com.nv.youNeverWait.rs.dto.NetMdViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

/**
 * @author Luciya
 *
 */
public interface NetmdTestDao {
	
	public ResponseDTO  create(NetMdDTO netMd);
	public ResponseDTO  update(NetMdDTO netMd);
	public ResponseDTO  delete(int netMdId);
	public NetMdViewResponseDTO view(int netMdId);
}
