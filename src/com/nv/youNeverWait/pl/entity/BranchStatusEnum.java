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
public enum BranchStatusEnum implements EnumDisplay{
	Active("active"),InActive("inactive"),Suspend("suspend");

	private String displayName;

	/**
	 * @param displayName
	 */
	private BranchStatusEnum(String displayName) {
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
	
public static BranchStatusEnum getEnum(String value) {
	if(value == null){
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
		se.setDisplayErrMsg(true);
		throw se;
	}
	for(BranchStatusEnum v : values()){        	
		if(value.equalsIgnoreCase(v.getDisplayName())){
			return v;}}
	ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
	se.setDisplayErrMsg(true);
	throw se;
}

}
