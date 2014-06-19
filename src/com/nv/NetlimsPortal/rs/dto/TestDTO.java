/**
 * TestDTO.java
 * @author netvarth
 *
 * Version 1.0 Sep 2, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.NetlimsPortal.rs.dto;

import com.nv.NetlimsPortal.pl.entity.TestTbl;



/**
 *
 *
 * @author Luciya Jose
 */
public class TestDTO {
	String testName;
	int id;
	int uid;
	float stdRate;
	
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
	 * @param testTbl
	 */
	public TestDTO(TestTbl testTbl) {
		this.id= testTbl.getId();
		this.uid=testTbl.getUid();
		this.testName= testTbl.getTestName();
		this.stdRate= 0;
	}
	/**
	 * @return the testName
	 */
	public String getTestName() {
		return testName;
	}
	/**
	 * @param testName the testName to set
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the stdRate
	 */
	public float getStdRate() {
		return stdRate;
	}
	/**
	 * @param stdRate the stdRate to set
	 */
	public void setStdRate(float stdRate) {
		this.stdRate = stdRate;
	}
	

}
