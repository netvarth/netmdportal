/**
 * SpecimenManager.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.NetlimsPortal.bl.service;

import java.util.Date;

import com.nv.NetlimsPortal.rs.dto.FilterDTO;
import com.nv.NetlimsPortal.rs.dto.HeaderDTO;
import com.nv.NetlimsPortal.rs.dto.ResponseDTO;
import com.nv.NetlimsPortal.rs.dto.RetrieveSpecimenResponse;
import com.nv.NetlimsPortal.rs.dto.SpecimenDTO;
import com.nv.NetlimsPortal.rs.dto.SpecimenListResponseDTO;
import com.nv.NetlimsPortal.rs.dto.SpecimenResponseDTO;



/**
 *
 *
 * @author Luciya Jose
 */
public interface SpecimenService {

	/**
	 * @param filter
	 * @return
	 */
	SpecimenListResponseDTO testSpecimenList(FilterDTO filter);

	/**
	 * @param specimen
	 * @return
	 */
	ResponseDTO createSpecimen(SpecimenDTO specimen);

	/**
	 * @param specimen
	 * @return
	 */
	ResponseDTO updateSpecimen(SpecimenDTO specimen);

	/**
	 * @param uid
	 * @return
	 */
	ResponseDTO deleteSpecimen(int uid);

	/**
	 * @param uid
	 * @return
	 */
	SpecimenResponseDTO viewSpecimen(int uid);
	
	/**
	 * @param header
	 * @param lastSyncTime
	 * @param currentSyncTime
	 * @return
	 */
	public RetrieveSpecimenResponse getSpecimens(HeaderDTO header,
			String lastSyncTime, Date currentSyncTime);

	/**
	 * @return
	 */
	 public RetrieveSpecimenResponse specimenList();
	

}
