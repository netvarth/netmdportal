/**
 * RetrieveSpecimenResponseDTO.java
 * @author netvarth
 *
 * Version 1.0 Sep 10, 2013
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
public class RetrieveSpecimenResponse {
 private List<SpecimenDTO>  specimenList= new ArrayList<SpecimenDTO>();
 private ErrorDTO error;
 private boolean success;
/**
 * @return the specimenList
 */
public List<SpecimenDTO> getSpecimenList() {
	return specimenList;
}
/**
 * @param specimenList the specimenList to set
 */
public void setSpecimenList(List<SpecimenDTO> specimenList) {
	this.specimenList = specimenList;
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
