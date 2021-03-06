/**
 * AppointmentPropertyEnum.java
 * @author netvarth
 *
 * Version 1.0 Jul 19, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.util.filter.queryBuilder;

import com.nv.youNeverWait.user.bl.impl.EnumDisplay;
import com.nv.youNeverWait.util.filter.core.Property;

/**
 * 
 * 
 * @author Luciya Jose
 */
public enum AppointmentPropertyEnum implements Property, EnumDisplay {
	/**
	 * 
	 */
	id("id", "id", "com.nv.youNeverWait.pl.entity.PatientAppointmentTbl", "", ""), 
	/**
	 * 
	 */
	patientId("patientId", "id", "com.nv.youNeverWait.pl.entity.NetmdPatientTbl","netmdPatientTbl", ""),
	/**
	 * 
	 */
	appointmentTime("appointmentTime","appointmentTime", "com.nv.youNeverWait.pl.entity.PatientAppointmentTbl","", "");
	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName;

	private AppointmentPropertyEnum(String displayName, String name,
			String entityName, String referenceName, String pathReferenceName) {
		this.displayName = displayName;
		this.fieldName = name;
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
	 * @param displayName
	 *            the displayName to set
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
	 * @param fieldName
	 *            the fieldName to set
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
	 * @param entityName
	 *            the entityName to set
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
	 * @param referenceName
	 *            the referenceName to set
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
	 * @param pathReferenceName
	 *            the pathReferenceName to set
	 */
	public void setPathReferenceName(String pathReferenceName) {
		this.pathReferenceName = pathReferenceName;
	}

}
