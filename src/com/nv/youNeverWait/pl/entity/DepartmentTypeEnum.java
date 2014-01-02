/**
 * DepartmentTypeEnum.java
 * @author netvarth
 *
 * Version 1.0 Jan 26, 2014
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
public enum DepartmentTypeEnum implements EnumDisplay{
	Obstetrics("obstetrics"),Cardiac("cardiac");

	private String displayName;

	/**
	 * @param displayName
	 */
	private DepartmentTypeEnum(String displayName) {
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

	public static DepartmentTypeEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidDepartmentType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(DepartmentTypeEnum v : values()){        	
			if(value.equals(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidDepartmentType);
		se.setDisplayErrMsg(true);
		throw se;
	}

}
