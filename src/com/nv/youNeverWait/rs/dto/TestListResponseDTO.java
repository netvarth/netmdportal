/**
 * TestListResponseDTO.java
 * @author netvarth
 *
 * Version 1.0 Sep 2, 2013
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
public class TestListResponseDTO {
	private List<TestDTO>  testList = new ArrayList<TestDTO>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
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
	 * @return the testList
	 */
	public List<TestDTO> getTestList() {
		return testList;
	}
	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	
	/**
	 * @param testList the testList to set
	 */
	public void setTestList(List<TestDTO> testList) {
		this.testList = testList;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}

}
