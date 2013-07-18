/**
 * LogUserTypeEnum.java
 * 
 * April 16,2013
 * 
 * @author Asha Chandran
 * 
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

public enum LogUserTypeEnum implements EnumDisplay{
	Admin("Admin"),Staff("Staff"),Nurse("Nurse"),Patient("Patient"),SuperAdmin("SuperAdmin"),Nil("Nil");

	private String displayName;

	/**
	 * @param displayName
	 */
	private LogUserTypeEnum(String displayName) {
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

	public static LogUserTypeEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(LogUserTypeEnum v : values()){        	
			if(value.equalsIgnoreCase(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
		se.setDisplayErrMsg(true);
		throw se;
	}

}
