/**
 * ResultService.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.youNeverWait.user.bl.service;

import java.util.Date;
import java.util.List;

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LimsPageSettingsBundle;
import com.nv.youNeverWait.rs.dto.OrderResultSyncDTO;
import com.nv.youNeverWait.rs.dto.OrderTestResultList;
import com.nv.youNeverWait.rs.dto.PageLayoutSettings;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveResultsResponseDTO;



/**
 * 
 */
public interface ResultService {
	

	/**
	 * @param patientId
	 * @return
	 */
	public ResultListResponseDTO listResult(String patientId);
	public List<RetrieveResultsResponseDTO> getPatientResults(String lastSyncTime, String passPhrase, int netMdBranchId,
			Date currentSyncTime);
	public OrderTestResultList retrieveResults(HeaderDTO header,
			String lastOrderSyncTime, Date currentSyncTime);
	/**
	 * @param orderResult 
	 * @param patientId 
	 * @param branchId 
	 * @return List<SyncResponse>
	 */
	public int processOrderResult(OrderResultSyncDTO orderResult,
			 Integer branchId);
	/**
	 * Mani E.V	
	 * @param bundle
	 */
	public void processPageSettings(LimsPageSettingsBundle bundle);
	/**
	 * Mani E.V	
	 * @param branchId
	 * @return
	 */
	public List<PageLayoutSettings> getPageSettings(int branchId);
}
