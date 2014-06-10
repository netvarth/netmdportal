/**
 * CaseStatusEnum.java
 * January 08, 2013
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

/**
 * @author Luciya Jose
 *
 */
public enum CaseStatusEnum implements EnumDisplay{
	Open("open"), Closed("closed"),Cancelled("cancelled");

	private String displayName;

	/**
	 * @param displayName
	 */
	private CaseStatusEnum(String displayName) {
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

	public static CaseStatusEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidCaseStatus);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(CaseStatusEnum v : values()){        	
			if(value.equalsIgnoreCase(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidCaseStatus);
		se.setDisplayErrMsg(true);
		throw se;
	}

}