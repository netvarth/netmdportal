/**
 * SeedDataDTO.java
 * @author netvarth
 *
 * Version 1.0 Aug 28, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 *
 * @author Luciya Jose
 */
public class SeedDataDTO {
	String result;
	List<TestSpecimenDTO> testSpecimen= new ArrayList<TestSpecimenDTO>();
	String uid;
	String abbreviation;
	float standardRate;
	String genericName;
	String minTimeRequired;
	String remarks;
	String informaticValue;
	boolean uploadStatus;
	String name;
	boolean active;

	/**
	 * @return the testSpecimen
	 */
	public List<TestSpecimenDTO> getTestSpecimen() {
		return testSpecimen;
	}
	/**
	 * @param testSpecimen the testSpecimen to set
	 */
	public void setTestSpecimen(List<TestSpecimenDTO> testSpecimen) {
		this.testSpecimen = testSpecimen;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Object result) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper maper = new ObjectMapper();
		String jsonRequest = maper.writeValueAsString(result);
		this.result = jsonRequest;
		}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @param abbreviation the abbreviation to set
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	/**
	 * @param standardRate the standardRate to set
	 */
	public void setStandardRate(float standardRate) {
		this.standardRate = standardRate;
	}
	/**
	 * @param genericName the genericName to set
	 */
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	/**
	 * @param minTimeRequired the minTimeRequired to set
	 */
	public void setMinTimeRequired(String minTimeRequired) {
		this.minTimeRequired = minTimeRequired;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @param informaticValue the informaticValue to set
	 */
	public void setInformaticValue(String informaticValue) {
		this.informaticValue = informaticValue;
	}
	/**
	 * @param uploadStatus the uploadStatus to set
	 */
	public void setUploadStatus(boolean uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}
	/**
	 * @return the standardRate
	 */
	public float getStandardRate() {
		return standardRate;
	}
	/**
	 * @return the genericName
	 */
	public String getGenericName() {
		return genericName;
	}
	/**
	 * @return the minTimeRequired
	 */
	public String getMinTimeRequired() {
		return minTimeRequired;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @return the informaticValue
	 */
	public String getInformaticValue() {
		return informaticValue;
	}
	/**
	 * @return the uploadStatus
	 */
	public boolean isUploadStatus() {
		return uploadStatus;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	
			
}
