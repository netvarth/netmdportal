/**
 * FrequencyEnum.java
 * @author netvarth
 *
 * Version 1.0 Aug 14, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
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
public enum FrequencyEnum implements EnumDisplay {
	Daily("daily"),Hourly("hourly"),Minutes("minutes");
	
	private String displayName;

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

	/**
	 * @param displayName
	 */
	private FrequencyEnum(String displayName) {
		this.displayName = displayName;
	}
	

	
	public static FrequencyEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvaliFrequencyType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(FrequencyEnum v : values()){        	
			if(value.equalsIgnoreCase(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvaliFrequencyType);
		se.setDisplayErrMsg(true);
		throw se;
	}
	
}
