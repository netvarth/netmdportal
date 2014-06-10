/**
 * ApplicationNameEnum.java
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

/**
 * @author Luciya
 * June 10 2014 Tuesday
 *
 */
public enum SourceTypeEnum implements EnumDisplay{
	Desktop("Desktop"),Android("Android"),IOS("IOS");

	private String displayName;

	/**
	 * @param displayName
	 */
	private SourceTypeEnum(String displayName) {
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
	
public static SourceTypeEnum getEnum(String value) {
	if(value == null){
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidSourceType);
		se.setDisplayErrMsg(true);
		throw se;
	}
	for(SourceTypeEnum v : values()){        	
		if(value.equalsIgnoreCase(v.getDisplayName())){
			return v;}}
	ServiceException se = new ServiceException(ErrorCodeEnum.InvalidSourceType);
	se.setDisplayErrMsg(true);
	throw se;
}

}
