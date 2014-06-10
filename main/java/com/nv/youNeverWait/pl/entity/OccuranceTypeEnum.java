/**
 * OccuranceTypeEnum.java
 *
 * Mar 13, 2013
 *
 * @author Luciya
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

public enum OccuranceTypeEnum implements EnumDisplay {
	None("None"),EndsOn("Ends on"),EndsDate("Ends Date");
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
	private OccuranceTypeEnum(String displayName) {
		this.displayName = displayName;
	}

	public static OccuranceTypeEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidOccuranceType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(OccuranceTypeEnum v : values()){        	
			if(value.equalsIgnoreCase(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidOccuranceType);
		se.setDisplayErrMsg(true);
		throw se;
	}
}
