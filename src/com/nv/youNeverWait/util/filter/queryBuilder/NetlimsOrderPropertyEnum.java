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
public enum NetlimsOrderPropertyEnum implements EnumDisplay, Property {
	/**
	 * 
	 */
	id("id", "id", "com.nv.youNeverWait.pl.entity.NetlimsOrderTbl", "", ""),
	/**
	 * 
	 */
	labId("labId","id","com.nv.youNeverWait.pl.entity.LabTbl","labBranchTbl","labTbl"),
	branchId("branchId", "id", "com.nv.youNeverWait.pl.entity.LabBranchTbl","labBranchTbl", ""),
	branchName("branchName", "name","com.nv.youNeverWait.pl.entity.LabBranchTbl","labBranchTbl", ""),
	/**
	 * 
	 */
	orderId("orderId","orderId","com.nv.youNeverWait.pl.entity.NetlimsOrderTbl","",""),
	/**
	 * 
	 */
	facilityId("facilityId","id","com.nv.youNeverWait.pl.entity.LabFacilityTbl","facilityResultTbls","labFacilityTbl"),
	facilityName("facilityname","name","com.nv.youNeverWait.pl.entity.LabFacilityTbl","facilityResultTbls","labFacilityTbl"),
	name("name","orderHeader","com.nv.youNeverWait.pl.entity.NetlimsOrderTbl","",""),
	referralName("referralName","orderHeader","com.nv.youNeverWait.pl.entity.NetlimsOrderTbl","",""),
	age("age","orderHeader","com.nv.youNeverWait.pl.entity.NetlimsOrderTbl","","")
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
	private NetlimsOrderPropertyEnum(String displayName, String fieldName,
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
