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
package com.nv.NetlimsPortal.bl.service;

import java.util.Date;


import com.nv.NetlimsPortal.rs.dto.HeaderDTO;
import com.nv.NetlimsPortal.rs.dto.OrderTestResultList;




/**
 * 
 */
public interface ResultService {
	
	
	public OrderTestResultList retrieveResults(HeaderDTO header,
			String lastOrderSyncTime, Date currentSyncTime);
	
}
