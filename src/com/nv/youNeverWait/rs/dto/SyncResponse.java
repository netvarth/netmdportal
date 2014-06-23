package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.user.bl.service.ActionEnum;



/**
 * @author Mani E.V
 *
 */
public class SyncResponse {
     
	private String localId;
	private Integer globalId;
	private String statusCode;
	private ActionEnum actionName;
	
	
	public String getLocalId() {
		return localId;
	}
	
	public void setLocalId(String localId) {
		this.localId = localId;
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
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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
