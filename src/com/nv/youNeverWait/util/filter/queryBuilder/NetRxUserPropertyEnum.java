package com.nv.youNeverWait.util.filter.queryBuilder;

import com.nv.youNeverWait.user.bl.impl.EnumDisplay;
import com.nv.youNeverWait.util.filter.core.Property;

public enum NetRxUserPropertyEnum implements Property, EnumDisplay{

	id("id","id","com.nv.youNeverWait.pl.entity.NetrxUserTbl","",""),
	branchId("branchId","id","com.nv.youNeverWait.pl.entity.NetrxUserTbl","netrxBranchTbl",""),
	firstName("firstName","firstName","com.nv.youNeverWait.pl.entity.NetrxUserTbl","",""), 
	email("email","email","com.nv.youNeverWait.pl.entity.NetrxUserTbl","",""),
	phone("phone","phone","com.nv.youNeverWait.pl.entity.NetrxUserTbl","",""),
	mobile("mobile","mobile","com.nv.youNeverWait.pl.entity.NetrxUserTbl","",""),
	address("address","address","com.nv.youNeverWait.pl.entity.NetrxUserTbl","",""),
	userType("userType","userType","com.nv.youNeverWait.pl.entity.NetrxUserTbl","","");
	
	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName;

	private NetRxUserPropertyEnum(String displayName,String name,String entityName,String referenceName,String pathReferenceName){
		this.displayName = displayName;
		this.fieldName = name;
		this.entityName = entityName;
		this.referenceName = referenceName;
		this.pathReferenceName =pathReferenceName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getPathReferenceName() {
		return pathReferenceName;
	}

	public void setPathReferenceName(String pathReferenceName) {
		this.pathReferenceName = pathReferenceName;
	}

	
}
