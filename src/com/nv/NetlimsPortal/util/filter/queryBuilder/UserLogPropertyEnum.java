package com.nv.NetlimsPortal.util.filter.queryBuilder;

import com.nv.NetlimsPortal.bl.impl.EnumDisplay;
import com.nv.NetlimsPortal.util.filter.core.Property;


public enum UserLogPropertyEnum implements Property, EnumDisplay{
	userName("userName","userName","com.nv.youNeverWait.pl.entity.LogTbl","",""),
	loginTime("loginTime","loginTime","com.nv.youNeverWait.pl.entity.LogTbl","",""),
	logoutTime("logoutTime","logoutTime","com.nv.youNeverWait.pl.entity.LogTbl","",""),
	ipAddress("ipAddress","ipAddress","com.nv.youNeverWait.pl.entity.LogTbl","",""),
	actionDate("actionDate","actionDate","com.nv.youNeverWait.pl.entity.LogTbl","",""),
	actionTime("actionTime","actionTime","com.nv.youNeverWait.pl.entity.LogTbl","",""),
	applicationName("applicationName","applicationName","com.nv.youNeverWait.pl.entity.LogTbl","",""),
	
	;
	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName;

	private UserLogPropertyEnum(String displayName,String name,String entityName,String referenceName,String pathReferenceName){
		this.displayName = displayName;
		this.fieldName = name;
		this.entityName = entityName;
		this.referenceName = referenceName;
		this.pathReferenceName =pathReferenceName;
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the referenceName
	 */
	public String getReferenceName() {
		return referenceName;
	}

	/**
	 * @param referenceName the referenceName to set
	 */
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	/**
	 * @return the pathReferenceName
	 */
	public String getPathReferenceName() {
		return pathReferenceName;
	}

	/**
	 * @param pathReferenceName the pathReferenceName to set
	 */
	public void setPathReferenceName(String pathReferenceName) {
		this.pathReferenceName = pathReferenceName;
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
