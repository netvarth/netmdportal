/**
 * UpdateTestResponseDTO.java
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
public class UpdateTestResponseDTO {
	private TestDTO test;
	private boolean success;
	private ErrorDTO error;
	
	public UpdateTestResponseDTO(){
		
	}

	public UpdateTestResponseDTO(TestDTO test, boolean success, ErrorDTO error) {
		super();
		this.test = test;
		this.success = success;
		this.error = error;
	}

	public TestDTO getTest() {
		return test;
	}

	public void setTest(TestDTO test) {
		this.test = test;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}
	
	
}

