/**
* ResultDao.java
* 
* @Author Sreeram
*
* Version 1.0 Jan 5, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.youNeverWait.user.pl.dao;

import java.util.Date;
import java.util.List;

import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveResultsResponseDTO;



/**
 * 
 */
public interface ResultDao {
	public ResultListResponseDTO listResult(String patientId);

	public List<RetrieveResultsResponseDTO> getPatientResults(String lastSyncTime, String passPhrase, int netMdBranchId,Date currentSyncTime);
	
}
