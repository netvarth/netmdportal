/**
 * NetRxUserTypeEnum.java
 * 
 * @author Asha chandran
 * 
 * May 08,2013
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

/**
 * @author Luciya Jose
 *
 */
public enum NetRxUserTypeEnum implements EnumDisplay{
	Admin("admin"),Owner("owner"),Staff("staff");

	private String displayName;

	/**
	 * @param displayName
	 */
	private NetRxUserTypeEnum(String displayName) {
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

	public static NetRxUserTypeEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(NetRxUserTypeEnum v : values()){        	
			if(value.equals(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
		se.setDisplayErrMsg(true);
		throw se;
	}

}
