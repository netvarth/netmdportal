package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.user.bl.service.ActionEnum;




/**
 *
 *
 * @author FairBorn
 */
public class FacilitySyncDTO {

	private FacilityInfo facility;
	private Integer globalId;
	private ActionEnum actionName;
	/**
	 * @return the facility
	 */
	public FacilityInfo getFacility() {
		return facility;
	}
	/**
	 * @param facility the facility to set
	 */
	public void setFacility(FacilityInfo facility) {
		this.facility = facility;
	}
	/**
	 * @return the globalId
	 */
	public Integer getGlobalId() {
		return globalId;
	}
	/**
	 * @param globalId the globalId to set
	 */
	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}
	/**
	 * @return the actionName
	 */
	public ActionEnum getActionName() {
		return actionName;
	}
	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(ActionEnum actionName) {
		this.actionName = actionName;
	}
	
}
