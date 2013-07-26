/**
 * DoctorAchievementDTO.java
 * 
 * Mar 25, 2013
 *netvarth
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Luciya Jos
 *
 */
public class DoctorAchievementDTO {
private int id;
private int doctorId;
private String achievement;
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
 * @return the achievement
 */
public String getAchievement() {
	return achievement;
}
/**
 * @param achievement the achievement to set
 */
public void setAchievement(String achievement) {
	this.achievement = achievement;
}

}
