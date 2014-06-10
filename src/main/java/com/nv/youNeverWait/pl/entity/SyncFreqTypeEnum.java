/**
 * SyncFreqTypeEnum.java
 * @author netvarth
 *
 * Version 1.0 Oct 9, 2013
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
public enum SyncFreqTypeEnum  implements EnumDisplay{
	DAILY("daily"), HOURLY("hourly"), MINUTES("minutes");
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
	private SyncFreqTypeEnum(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param name
	 * @param ordinal
	 */
	private SyncFreqTypeEnum(String name, int ordinal) {
		
	}
	
	public static SyncFreqTypeEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidSyncFreqType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(SyncFreqTypeEnum v : values()){        	
			if(value.equalsIgnoreCase(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidSyncFreqType);
		se.setDisplayErrMsg(true);
		throw se;
    }
}

