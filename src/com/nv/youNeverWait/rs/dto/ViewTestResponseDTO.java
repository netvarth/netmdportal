/**
 * ViewTestResponseDTO.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Luciya Jose
 */
public class ViewTestResponseDTO {
	private String uid;
	private String name;
	private String genericName;
	private String abbreviation;
	private String minTimeRequired;
	private float standardRate;
	private String normalRange;
	private String result;
	private String remarks;
	private boolean uploadStatus;
	private boolean specimenentryStatus;
	private boolean machineEntryStatus;
	private List<TestSpecimenDTO>testSpecimens =new ArrayList<TestSpecimenDTO>();
	private ErrorDTO error; 
	private boolean success;
	private boolean active;
	
	
	public ViewTestResponseDTO() {
		
	}
	
	public ViewTestResponseDTO(String uid, String name, String genericName,
			String abbreviation, String minTimeRequired, float standardRate,
			String normalRange, String result, String remarks,
			boolean uploadStatus, boolean specimenentryStatus, boolean machineEntryStatus, List<TestSpecimenDTO> testSpecimens,
			ErrorDTO error, boolean success, boolean active) {
		super();
		this.uid = uid;
		this.name = name;
		this.genericName = genericName;
		this.abbreviation = abbreviation;
		this.minTimeRequired = minTimeRequired;
		this.standardRate = standardRate;
		this.normalRange = normalRange;
		this.result = result;
		this.remarks = remarks;
		this.uploadStatus = uploadStatus;
		this.specimenentryStatus = specimenentryStatus;
		this.machineEntryStatus = machineEntryStatus;
		this.testSpecimens = testSpecimens;
		this.error = error;
		this.success = success;
		this.active = active;
	}

	/**
	 * @return the testSpecimens
	 */
	public List<TestSpecimenDTO> getTestSpecimens() {
		return testSpecimens;
	}


	/**
	 * @param testSpecimens the testSpecimens to set
	 */
	public void setTestSpecimens(List<TestSpecimenDTO> testSpecimens) {
		this.testSpecimens = testSpecimens;
	}
	

	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the genericName
	 */
	public String getGenericName() {
		return genericName;
	}
	/**
	 * @param genericName the genericName to set
	 */
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	/**
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}
	/**
	 * @param abbreviation the abbreviation to set
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	/**
	 * @return the standardRate
	 */
	public float getStandardRate() {
		return standardRate;
	}
	/**
	 * @param standardRate the standardRate to set
	 */
	public void setStandardRate(float standardRate) {
		this.standardRate = standardRate;
	}

	public String getNormalRange() {
		return normalRange;
	}

	public void setNormalRange(String normalRange) {
		this.normalRange = normalRange;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(boolean uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public String getMinTimeRequired() {
		return minTimeRequired;
	}

	public void setMinTimeRequired(String minTimeRequired) {
		this.minTimeRequired = minTimeRequired;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the specimenentryStatus
	 */
	public boolean isSpecimenentryStatus() {
		return specimenentryStatus;
	}

	/**
	 * @param specimenentryStatus the specimenentryStatus to set
	 */
	public void setSpecimenentryStatus(boolean specimenentryStatus) {
		this.specimenentryStatus = specimenentryStatus;
	}

	public boolean isMachineEntryStatus() {
		return machineEntryStatus;
	}

	public void setMachineEntryStatus(boolean machineEntryStatus) {
		this.machineEntryStatus = machineEntryStatus;
	}
	
	
}
