/**
 * ConfigDataDetails.java
 * @author netvarth
 *
 * Version 1.0 Aug 28, 2013
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
public class ConfigDataDetails {
	List<SpecimenList> specimen=new ArrayList<SpecimenList>();
	List<SeedDataDTO> test= new ArrayList<SeedDataDTO>();

	
	/**
	 * @return the specimen
	 */
	public List<SpecimenList> getSpecimen() {
		return specimen;
	}

	/**
	 * @param specimen the specimen to set
	 */
	public void setSpecimen(List<SpecimenList> specimen) {
		this.specimen = specimen;
	}

	/**
	 * @return the test
	 */
	public List<SeedDataDTO> getTest() {
		return test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(List<SeedDataDTO> test) {
		this.test = test;
	}

	
}
