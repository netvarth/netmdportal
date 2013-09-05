/**
 * AddTestDTO.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;
import java.util.List;
/**
 *
 *
 * @author Luciya Jose
 */
public class AddTestDTO {

	private String uid;
	private String name;
	private String genericName;
	private String abbreviation;
	private String minTimeRequired;
	private List<TestSpecimenDTO> testSpecimen;
	private String informaticValue;
	private String result;
	private float standardRate;
	private String remarks;
	private boolean uploadStatus;
	private String normalRange;
	private boolean specimenEntryStatus;
	private boolean machineEntryStatus;
	
	public AddTestDTO(){
		
	}

	public AddTestDTO(String uid, String name, String genericName,
			String abbreviation, String minTimeRequired,
			List<TestSpecimenDTO> testSpecimen, String informaticValue,
			String result, float standardRate, String remarks,
			boolean uploadStatus,String normalRange, boolean specimenEntryStatus, boolean machineEntryStatus) {
		super();
		this.uid = uid;
		this.name = name;
		this.genericName = genericName;
		this.abbreviation = abbreviation;
		this.minTimeRequired = minTimeRequired;
		this.testSpecimen = testSpecimen;
		this.informaticValue = informaticValue;
		this.result = result;
		this.standardRate = standardRate;
		this.remarks = remarks;
		this.uploadStatus = uploadStatus;
		this.normalRange = normalRange;
		this.specimenEntryStatus = specimenEntryStatus;
		this.machineEntryStatus = machineEntryStatus;
	}

	public String getNormalRange() {
		return normalRange;
	}

	public void setNormalRange(String normalRange) {	
		this.normalRange = normalRange;	
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getMinTimeRequired() {
		return minTimeRequired;
	}

	public void setMinTimeRequired(String minTimeRequired) {
		this.minTimeRequired = minTimeRequired;
	}

	public List<TestSpecimenDTO> getTestSpecimen() {
		return testSpecimen;
	}

	public void setTestSpecimen(List<TestSpecimenDTO> testSpecimen) {
		this.testSpecimen = testSpecimen;
	}

	public String getInformaticValue() {
		return informaticValue;
	}

	public void setInformaticValue(String informaticValue) {
		this.informaticValue = informaticValue;
	}

	public String getResult() {
		return result;
	}

	public void setResult(Object result) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper maper = new ObjectMapper();
		String jsonRequest = maper.writeValueAsString(result);		
		this.result = jsonRequest;		
	}

	public float getStandardRate() {
		return standardRate;
	}

	public void setStandardRate(float standardRate) {
		this.standardRate = standardRate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks){	
		this.remarks = remarks;		
	}

	public boolean isUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(boolean uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	/**
	 * @return the specimenEntryStatus
	 */
	public boolean isSpecimenEntryStatus() {
		return specimenEntryStatus;
	}

	/**
	 * @param specimenEntryStatus the specimenEntryStatus to set
	 */
	public void setSpecimenEntryStatus(boolean specimenEntryStatus) {
		this.specimenEntryStatus = specimenEntryStatus;
	}

	public boolean isMachineEntryStatus() {
		return machineEntryStatus;
	}

	public void setMachineEntryStatus(boolean machineEntryStatus) {
		this.machineEntryStatus = machineEntryStatus;
	}

	
}

