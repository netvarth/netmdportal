/**
 * ActionNameEnum.java
 *
 * Dec 19, 2012
 *
 * @author Luciya Jose
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;




/**
 * @author Luciya Jose
 *
 */
public enum ActionNameEnum implements EnumDisplay{
	ADD("Add"), UPDATE("Update"), DELETE("Delete");
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
	private ActionNameEnum(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @param name
	 * @param ordinal
	 */
	private ActionNameEnum(String name, int ordinal) {
		
	}
	
	public static ActionNameEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidActionType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(ActionNameEnum v : values()){        	
			if(value.equalsIgnoreCase(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidActionType);
		se.setDisplayErrMsg(true);
		throw se;
    }
}
