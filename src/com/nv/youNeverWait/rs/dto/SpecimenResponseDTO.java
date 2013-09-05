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
	private String name;
	private String unit;
	private ErrorDTO error;
	private boolean success;
	
	public SpecimenResponseDTO(){
		
	}
	
	public SpecimenResponseDTO(int uid, String name, String unit,
			ErrorDTO error, boolean success) {
		super();
		this.uid = uid;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
