/**
 * OperatorEnum.java
 *
 * Aug 18, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.util.filter.queryBuilder;

import com.nv.youNeverWait.user.bl.impl.EnumDisplay;


public enum OperatorEnum implements EnumDisplay{
	 equal("eq"),
	 notEqual("neq"),
	 like("like"),
	 greaterThan("gt"),
	 greaterThanOrEqualTo("ge"),
	 lessThan("lt"),
	 lessThanOrEqualTo("le");
	
	 private String displayName;
	 
	 private OperatorEnum(String displayName){
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
}
