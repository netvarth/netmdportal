/**
 * LabPropertyEnum.java
 *
 * @Author Asha Chandran
 *
 * Jan 23, 2013 
 */
package com.nv.youNeverWait.util.filter.queryBuilder;

import com.nv.youNeverWait.user.bl.impl.EnumDisplay;
import com.nv.youNeverWait.util.filter.core.Property;



public enum LabUserPropertyEnum implements Property, EnumDisplay{

	id("id","id","com.nv.youNeverWait.pl.entity.LabUserTbl","",""),
	branchId("branchId","id","com.nv.youNeverWait.pl.entity.LabBranchTbl","labUserBranchTbls","labBranchTbl"),
	name("name","name","com.nv.youNeverWait.pl.entity.LabUserTbl","",""),
	email("email","email","com.nv.youNeverWait.pl.entity.LabUserTbl","",""),
	phone("phone","phone","com.nv.youNeverWait.pl.entity.LabUserTbl","",""),
	mobile("mobile","mobile","com.nv.youNeverWait.pl.entity.LabUserTbl","",""),
	address("address","address","com.nv.youNeverWait.pl.entity.LabUserTbl","",""),
	userType("userType","userType","com.nv.youNeverWait.pl.entity.LabUserTbl","","");
	
	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName;

	private LabUserPropertyEnum(String displayName,String name,String entityName,String referenceName,String pathReferenceName){
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
