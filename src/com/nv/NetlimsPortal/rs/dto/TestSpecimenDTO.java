/**
 * TestSpecimenDTO.java
 * @author netvarth
 *
 * Version 1.0 Aug 28, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.NetlimsPortal.rs.dto;

/**
 *
 *
 * @author Luciya Jose
 */
public class TestSpecimenDTO {
	private int specimenUid;
	private float quantity;
	private String specimenName;
	private String actionName;
	
	public TestSpecimenDTO(){
		
	}

	public TestSpecimenDTO(int specimenUid, float quantity, String name,String actionName) {
		super();
		this.specimenUid = specimenUid;
		this.quantity = quantity;
		this.specimenName = name;
		this.actionName = actionName;
	}



	/**
	 * @return the specimenUid
	 */
	public int getSpecimenUid() {
		return specimenUid;
	}

	/**
	 * @return the quantity
	 */
	public float getQuantity() {
		return quantity;
	}


	/**
	 * @param specimenUid the specimenUid to set
	 */
	public void setSpecimenUid(int specimenUid) {
		this.specimenUid = specimenUid;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
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

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
		
}