/**
 * TestService.java
 * @author netvarth
 *
 * Version 1.0 Sep 2, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.NetlimsPortal.bl.service;

import java.util.Date;

import com.nv.NetlimsPortal.rs.dto.AddTestDTO;
import com.nv.NetlimsPortal.rs.dto.DeleteTestResponseDTO;
import com.nv.NetlimsPortal.rs.dto.FilterDTO;
import com.nv.NetlimsPortal.rs.dto.HeaderDTO;
import com.nv.NetlimsPortal.rs.dto.ResponseDTO;
import com.nv.NetlimsPortal.rs.dto.RetrieveTestResponse;
import com.nv.NetlimsPortal.rs.dto.TestListResponseDTO;
import com.nv.NetlimsPortal.rs.dto.UpdateTestResponseDTO;
import com.nv.NetlimsPortal.rs.dto.ViewTestResponseDTO;



/**
 *
 *
 * @author Luciya Jose
 */
public interface TestService {

	/**
	 * @param filter
	 * @return
	 */
	TestListResponseDTO testList(FilterDTO filter);

	/**
	 * @param testDTO
	 * @return
	 */
	ResponseDTO createTest(AddTestDTO testDTO);

	/**
	 * @param testDTO
	 * @return
	 */
	UpdateTestResponseDTO updateTest(AddTestDTO testDTO);

	/**
	 * @param uid
	 * @return
	 */
	DeleteTestResponseDTO deleteTest(String uid);

	/**
	 * @param uid
	 * @return
	 */
	ViewTestResponseDTO viewTest(String uid);
	
	/**
	 * @param header
	 * @param lastSyncTime
	 * @param currentSyncTime
	 * @return
	 */
	public RetrieveTestResponse getTests(HeaderDTO header,
			String lastSyncTime, Date currentSyncTime);
	
	

}
