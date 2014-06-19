/**
 * SpecimenListResponseDTO.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
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
public class SpecimenListResponseDTO {
	private List<SpecimenDTO>  specimenList = new ArrayList<SpecimenDTO>();
	private Long count;
	private ErrorDTO error;
	private boolean success;
	/**
	 * @param specimenList the specimenList to set
	 */
	public void setSpecimenList(List<SpecimenDTO> specimenList) {
		this.specimenList = specimenList;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the specimenList
	 */
	public List<SpecimenDTO> getSpecimenList() {
		return specimenList;
	}
	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

}
