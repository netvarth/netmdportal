/**
 * NetRxBranchPropertyEnum.java
 *
 * @Author Luciya Jos
 * May 14, 2013 
 */
package com.nv.youNeverWait.util.filter.queryBuilder;

import com.nv.youNeverWait.user.bl.impl.EnumDisplay;
import com.nv.youNeverWait.util.filter.core.Property;

/**
 * @author netvarth
 *
 */
public enum NetRxBranchPropertyEnum implements Property, EnumDisplay {
	name("name","name","com.nv.youNeverWait.pl.entity.NetrxBranchTbl","",""),
	mobileNumber("mobileNumber","mobile","com.nv.youNeverWait.pl.entity.NetrxBranchTbl","",""),
	phone("phone","phone","com.nv.youNeverWait.pl.entity.NetrxBranchTbl","",""),
	address("address","address","com.nv.youNeverWait.pl.entity.NetrxBranchTbl","",""),
	netRxId("netRxId","id","com.nv.youNeverWait.pl.entity.NetrxTbl","netrxTbl",""),
	status("status","status","com.nv.youNeverWait.pl.entity.NetrxBranchTbl","",""),
	
	;
	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName;
	
	/**
	 * @param displayName
	 * @param fieldName
	 * @param entityName
	 * @param referenceName
	 * @param pathReferenceName
	 */
	private NetRxBranchPropertyEnum(String displayName, String fieldName,
			String entityName, String referenceName, String pathReferenceName) {
		this.displayName = displayName;
		this.fieldName = fieldName;
		this.entityName = entityName;
		this.referenceName = referenceName;
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
	
	
}
