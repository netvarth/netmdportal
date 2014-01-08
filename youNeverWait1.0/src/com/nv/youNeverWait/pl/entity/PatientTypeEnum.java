/**
 * PatientTypeEnum.java
 * @author netvarth
 *
 * Version 1.0 Jan 7, 2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

/**
 *
 *
 * @author Luciya Jose
 */
public enum PatientTypeEnum implements EnumDisplay {
	InPatient("inpatient"),OutPatient("outPatient");

	private String displayName;

	/**
	 * @param displayName
	 */
	private PatientTypeEnum(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public static PatientTypeEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidPatientType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(PatientTypeEnum v : values()){        	
			if(value.equalsIgnoreCase(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidPatientType);
		se.setDisplayErrMsg(true);
		throw se;
	}

}