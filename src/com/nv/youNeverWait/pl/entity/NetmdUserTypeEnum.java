/**
 * 
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

/**
 * @author Luciya Jose
 *
 */
public enum NetmdUserTypeEnum implements EnumDisplay{
	Admin("admin"),Owner("owner"),Doctor("doctor"),Staff("staff"),Nurse("nurse"),Patient("patient");

	private String displayName;

	/**
	 * @param displayName
	 */
	private NetmdUserTypeEnum(String displayName) {
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

	public static NetmdUserTypeEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(NetmdUserTypeEnum v : values()){        	
			if(value.equals(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
		se.setDisplayErrMsg(true);
		throw se;
	}

}
