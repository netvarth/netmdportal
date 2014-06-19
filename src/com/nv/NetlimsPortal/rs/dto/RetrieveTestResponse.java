/**
 * TestResponseDTO.java
 * @author netvarth
 *
 * Version 1.0 Sep 6, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.NetlimsPortal.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Luciya Jose
 */
public class RetrieveTestResponse {
	private List<AddTestDTO> testList= new ArrayList<AddTestDTO>();
	private ErrorDTO error;
	private boolean success;
	/**
	 * @return the testList
	 */
	public List<AddTestDTO> getTestList() {
		return testList;
	}
	/**
	 * @param testList the testList to set
	 */
	public void setTestList(List<AddTestDTO> testList) {
		this.testList = testList;
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

}
