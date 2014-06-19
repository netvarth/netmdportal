/**
 * LabPropertyEnum.java
 *
 * @Author Asha Chandran
 *
 * Jan 23, 2013 
 */
package com.nv.NetlimsPortal.util.filter.queryBuilder;

import com.nv.NetlimsPortal.bl.impl.EnumDisplay;
import com.nv.NetlimsPortal.util.filter.core.Property;






public enum LabPropertyEnum implements Property, EnumDisplay{

	id("id","id","com.nv.youNeverWait.pl.entity.LabTbl","",""),
	name("name","name","com.nv.youNeverWait.pl.entity.LabTbl","",""),
	status("status","status","com.nv.youNeverWait.pl.entity.LabTbl","",""),
	ownerName("ownerName","ownerName","com.nv.youNeverWait.pl.entity.LabTbl","",""),
	ownerEmail("ownerEmail","ownerEmail","com.nv.youNeverWait.pl.entity.LabTbl","",""),
	branchName("branchName","name","com.nv.youNeverWait.pl.entity.LabBranchTbl","labBranchTbls",""),
	userId("userId","id","com.nv.youNeverWait.pl.entity.LabUserTbl","labUserBranchTbls","labUserTbl")
	;
	
	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName;

	private LabPropertyEnum(String displayName,String name,String entityName,String referenceName,String pathReferenceName){
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
