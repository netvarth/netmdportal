/**
 * LabUserTypeEnum.java
 */
package com.nv.NetlimsPortal.pl.entity;

import com.nv.NetlimsPortal.bl.impl.EnumDisplay;
import com.nv.NetlimsPortal.exception.ServiceException;





/**
 * @author Luciya Jose
 *
 */
public enum LabUserTypeEnum implements EnumDisplay{

	Admin("admin"),Owner("owner"),Patient("patient");

	private String displayName;

	/**
	 * @param displayName
	 */
	private LabUserTypeEnum(String displayName) {
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
	
		
	public static LabUserTypeEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(LabUserTypeEnum v : values()){        	
			if(value.equalsIgnoreCase(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
		se.setDisplayErrMsg(true);
		throw se;
	}
	
}
