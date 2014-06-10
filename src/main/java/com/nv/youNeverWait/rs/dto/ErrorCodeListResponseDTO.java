/**
 * ErrorCodeListResponseDTO.java
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;


/**
 * @author AshaChandran
 *
 */
public class ErrorCodeListResponseDTO {
	private List<Error> error= new ArrayList<Error>();

	/**
	 * @return the error
	 */
	public List<Error> getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(List<Error> error) {
		this.error = error;
	}

	/**
	 * @param error
	 */
	public ErrorCodeListResponseDTO(List<Error> error) {
		super();
		this.error = error;
	}

	/**
	 * 
	 */
	public ErrorCodeListResponseDTO() {
		
	}
	

}
