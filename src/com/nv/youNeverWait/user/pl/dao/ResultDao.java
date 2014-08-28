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

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.OrderResultSyncDTO;
import com.nv.youNeverWait.rs.dto.OrderTestResultList;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveResultsResponseDTO;



/**
 * 
 */
public interface ResultDao {
	/**
	 * @param patientId
	 * @return ResultListResponseDTO resultList Response
	 */
	public ResultListResponseDTO listResult(String patientId);

	/**
	 * @param lastSyncTime
	 * @param passPhrase
	 * @param netMdBranchId
	 * @param currentSyncTime
	 * @return  List<RetrieveResultsResponseDTO>
	 */
	public List<RetrieveResultsResponseDTO> getPatientResults(String lastSyncTime, String passPhrase, int netMdBranchId,Date currentSyncTime);

	/**
	 * @param header
	 * @param lastSyncTime
	 * @param currentSyncTime
	 * @return OrderTestResultList
	 */
	public OrderTestResultList retrieveResults(HeaderDTO header, String lastSyncTime,
			Date currentSyncTime);

	/**
	 * @param orderResult
	 * @param patientId
	 * @param branchId 
	 * @param patientId 
	 * @return globalid
	 */
	public int processOrderResult(OrderResultSyncDTO orderResult, int branchId, int patientId);
	
}
