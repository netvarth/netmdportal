/**
 * 
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

/**
 * @author Asha Chandran
 *
 */
public enum StatusEnum implements EnumDisplay{
	Active("active"),InActive("inactive"),Suspend("suspend");

	private String displayName;

	/**
	 * @param displayName
	 */
	private StatusEnum(String displayName) {
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
	
public static StatusEnum getEnum(String value) {
	if(value == null){
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidStatus);
		se.setDisplayErrMsg(true);
		throw se;
	}
	for(StatusEnum v : values()){        	
		if(value.equalsIgnoreCase(v.getDisplayName())){
			return v;}}
	ServiceException se = new ServiceException(ErrorCodeEnum.InvalidStatus);
	se.setDisplayErrMsg(true);
	throw se;
}

}
