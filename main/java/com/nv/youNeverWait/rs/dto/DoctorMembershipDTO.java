/**
 * DoctorMembershipDTO.java 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Luciya Jos
 *
 */
public class DoctorMembershipDTO {
	private int id;
	private int doctorId;
	private String membership;
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
	 * @return the membership
	 */
	public String getMembership() {
		return membership;
	}
	/**
	 * @param membership the membership to set
	 */
	public void setMembership(String membership) {
		this.membership = membership;
	}
	

}
