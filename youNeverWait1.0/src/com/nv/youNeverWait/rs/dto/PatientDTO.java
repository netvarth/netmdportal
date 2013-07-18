/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Luciya Jose
 *
 */
public class PatientDTO {
	private HeaderDTO header;
	private PatientDetail patientDetail;
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
	 * @return the patientDetail
	 */
	public PatientDetail getPatientDetail() {
		return patientDetail;
	}
	/**
	 * @param patientDetail the patientDetail to set
	 */
	public void setPatientDetail(PatientDetail patientDetail) {
		this.patientDetail = patientDetail;
	}
	/**
	 * @param header
	 * @param patientDetail
	 */
	public PatientDTO(HeaderDTO header, PatientDetail patientDetail) {
		super();
		this.header = header;
		this.patientDetail = patientDetail;
	}
	/**
	 * 
	 */
	public PatientDTO() {
		
	}
	

}
