/**
 * RepeatTypeEnum.java
 *
 * Mar 13, 2013
 *
 * @author Luciya
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

public enum RepeatTypeEnum implements EnumDisplay {
	NONE("None"),DAILY("Daily"),WEEKLY("Weekly"),MONTHILY_DAY_OF_THE_MONTH("Monthily day of the month"),MONTHILY_DAY_OF_THE_WEEK("Monthily day of the week");

	
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
	private RepeatTypeEnum(String displayName) {
		this.displayName = displayName;
	}

	public static RepeatTypeEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidRepeatType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(RepeatTypeEnum v : values()){        	
			if(value.equalsIgnoreCase(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidRepeatType);
		se.setDisplayErrMsg(true);
		throw se;
	}
}
