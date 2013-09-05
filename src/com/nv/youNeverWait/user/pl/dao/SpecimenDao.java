/**
 * SpecimenDao.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.pl.dao;

import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SpecimenDTO;
import com.nv.youNeverWait.rs.dto.SpecimenResponseDTO;

/**
 *
 *
 * @author Luciya Jose
 */
public interface SpecimenDao {

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

}
