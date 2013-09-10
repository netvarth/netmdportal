/**
 * SpecimenResponseDTO.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

/**
 *
 *
 * @author Luciya Jose
 */
public class SpecimenResponseDTO {

	
	private int uid;	
	private String specimenName;
	private String unit;
	private ErrorDTO error;
	private boolean success;
	
	public SpecimenResponseDTO(){
		
	}
	
	public SpecimenResponseDTO(int uid, String specimenName, String unit,
			ErrorDTO error, boolean success) {
		super();
		this.uid = uid;
		this.specimenName = specimenName;
		this.unit = unit;
		this.error = error;
		this.success = success;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	
	/**
	 * @return the specimenName
	 */
	public String getSpecimenName() {
		return specimenName;
	}

	/**
	 * @param specimenName the specimenName to set
	 */
	public void setSpecimenName(String specimenName) {
		this.specimenName = specimenName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
