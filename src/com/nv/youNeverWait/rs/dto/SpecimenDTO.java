/**
 * SpecimenDTO.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.pl.entity.SpecimenTbl;

/**
 *
 *
 * @author Luciya Jose
 */
public class SpecimenDTO {
	private String specimenName;
	private String unit;
	private int uid;
	/**
	 * @param specimenName the specimenName to set
	 */
	public void setSpecimenName(String specimenName) {
		this.specimenName = specimenName;
	}
	
	
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}


	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}


	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}


	/**
	 * @param uid the uid to set
	 */
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
	 * @param specimenName
	 * @param quantity
	 * @param uid
	 */
	public SpecimenDTO(SpecimenTbl specimenTbl) {
		super();
		this.specimenName = specimenTbl.getName();
		this.unit = specimenTbl.getUnit();
		this.uid = specimenTbl.getUid();
	}
	

}
