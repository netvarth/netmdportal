/**
 * LabStatusEnum.java
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

/**
 * @author Asha Chandran
 *
 */
public enum LabStatusEnum implements EnumDisplay{
	Active("active"),InActive("inactive"),Suspend("suspend");

	private String displayName;

	/**
	 * @param displayName
	 */
	private LabStatusEnum(String displayName) {
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
	
public static LabStatusEnum getEnum(String value) {
	if(value == null){
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidStatus);
		se.setDisplayErrMsg(true);
		throw se;
	}
	for(LabStatusEnum v : values()){        	
		if(value.equalsIgnoreCase(v.getDisplayName())){
			return v;}}
	ServiceException se = new ServiceException(ErrorCodeEnum.InvalidStatus);
	se.setDisplayErrMsg(true);
	throw se;
}

}
