/**
 * 
 */
package com.nv.youNeverWait.util.filter.queryBuilder;

import com.nv.youNeverWait.user.bl.impl.EnumDisplay;
import com.nv.youNeverWait.util.filter.core.Property;

/**
 * @author Mani E.V
 *
 */
public enum PatientResultPropertyEnum implements EnumDisplay, Property {
	/**
	 * 
	 */
	id("id", "id", "com.nv.youNeverWait.pl.entity.PatientResultTbl", "", ""),
	/**
	 * 
	 */
	branchId("branchId", "id", "com.nv.youNeverWait.pl.entity.LabBranchTbl","netlimsOrderTbl", "labBranchTbl"),
	branchName("branchName", "name","com.nv.youNeverWait.pl.entity.LabBranchTbl","netlimsOrderTbl", "labBranchTbl"),
	/**
	 * 
	 */
	orderId("orderId","orderId","com.nv.youNeverWait.pl.entity.NetlimsOrderTbl","netlimsOrderTbl",""),
	/**
	 * 
	 */
	
	name("name","orderHeader","com.nv.youNeverWait.pl.entity.NetlimsOrderTbl","netlimsOrderTbl",""),
	email("email","email","com.nv.youNeverWait.pl.entity.PatientTbl","netlimsPatientTbl","patientTbl"),
	referralName("referralName","orderHeader","com.nv.youNeverWait.pl.entity.NetlimsOrderTbl","netlimsOrderTbl",""),
	age("age","orderHeader","com.nv.youNeverWait.pl.entity.NetlimsOrderTbl","netlimsOrderTbl","")
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
	private PatientResultPropertyEnum(String displayName, String fieldName,
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
