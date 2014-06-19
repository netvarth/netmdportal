/**
 * DeleteTestResponseDTO.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
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
public class DeleteTestResponseDTO {
	private String uid;
	private boolean success;
	private ErrorDTO error;
	
	public DeleteTestResponseDTO(){
		
	}

	public DeleteTestResponseDTO(String uid, boolean success,
			ErrorDTO error) {
		super();
		this.uid = uid;
		this.success = success;
		this.error = error;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

