/**
 * DoctorExpertiseDTO.java
 * 
 * Mar 25, 2013
 *netvarth
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author netvarth
 *
 */
public class DoctorExpertiseDTO {
	private int id;
	private int doctorId;
	private String expertise;
	private String actionName;
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
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
	 * @return the expertise
	 */
	public String getExpertise() {
		return expertise;
	}
	/**
	 * @param expertise the expertise to set
	 */
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	

}
