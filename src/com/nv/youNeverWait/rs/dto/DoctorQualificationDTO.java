/**
 * DoctorQualificationDTO.java
 *
 * Dec 19, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * 
 */
public class DoctorQualificationDTO {
	private int id;
	private int doctorId;
	private String educationalDegree ;
	private String passedOutDate;
	private String institution;
	private String actionName;
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
	 * @return the educationalDegree
	 */
	public String getEducationalDegree() {
		return educationalDegree;
	}
	/**
	 * @param educationalDegree the educationalDegree to set
	 */
	public void setEducationalDegree(String educationalDegree) {
		this.educationalDegree = educationalDegree;
	}
	/**
	 * @return the passedOutDate
	 */
	public String getPassedOutDate() {
		return passedOutDate;
	}
	/**
	 * @param passedOutDate the passedOutDate to set
	 */
	public void setPassedOutDate(String passedOutDate) {
		this.passedOutDate = passedOutDate;
	}
	/**
	 * @return the institution
	 */
	public String getInstitution() {
		return institution;
	}
	/**
	 * @param institution the institution to set
	 */
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}
	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
}
