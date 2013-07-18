/**
 * ScheduleStatusEnum.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 19, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;



/**
 * 
 */
public enum ScheduleStatusEnum implements EnumDisplay {
	
	VACATION("Vacation"), WORKING_HOURS("Working Hours");
	private String displayName;
	/**
	 * @param displayName
	 */
	private ScheduleStatusEnum(String displayName) {
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
	
	public static ScheduleStatusEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidStatus);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(ScheduleStatusEnum v : values()){        	
			if(value.equalsIgnoreCase(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidStatus);
		se.setDisplayErrMsg(true);
		throw se;
	}
}
