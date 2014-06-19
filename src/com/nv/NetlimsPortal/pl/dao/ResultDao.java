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
package com.nv.NetlimsPortal.pl.dao;

import java.util.Date;
import java.util.List;

import com.nv.NetlimsPortal.rs.dto.HeaderDTO;
import com.nv.NetlimsPortal.rs.dto.OrderTestResultList;





/**
 * 
 */
public interface ResultDao {
	
	public OrderTestResultList retrieveResults(HeaderDTO header, String lastSyncTime,
			Date currentSyncTime);
	
}
