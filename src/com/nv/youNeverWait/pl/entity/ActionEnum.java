/**
 * ActionEnum.java
 * @author Mani E.V 
 *
 * Version 1.0 05-Jun-2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

/**
 *
 *
 * @author Mani E.V
 */
public enum ActionEnum implements EnumDisplay{
	/**
	 * 
	 */
	CREATE("Create"), /**
	 * 
	 */
	UPDATE("Update"), /**
	 * 
	 */
	DELETE("Delete");
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
	private ActionEnum(String displayName) {
		this.displayName = displayName;
	}
}
