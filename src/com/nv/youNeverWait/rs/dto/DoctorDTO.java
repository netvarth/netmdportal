/**
 * DoctorDTO.java
 *
 * Dec 19, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * 
 */
public class DoctorDTO {
	private HeaderDTO header;
	private DoctorDetail doctor;

	
	
	/**
	 * @return the header
	 */
	public HeaderDTO getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(HeaderDTO header) {
		this.header = header;
	}
	/**
	 * @return the doctor
	 */
	public DoctorDetail getDoctor() {
		return doctor;
	}
	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(DoctorDetail doctor) {
		this.doctor = doctor;
	}
	/**
	 * 
	 */
	public DoctorDTO() {
		
	}
	/**
	 * @param header
	 * @param doctor
	 */
	public DoctorDTO(HeaderDTO header, DoctorDetail doctor) {
		super();
		this.header = header;
		this.doctor = doctor;
	}
	
	

}
