/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Luciya Jose
 *
 */
public class PatientResponse {
	private int id;
	private int globalId;
	private String createDateTime;
	private String updateDateTime;
	private ErrorDTO error;
	private boolean success;
	private String actionName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGlobalId() {
		return globalId;
	}
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
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
	
	/**
	 * @return the createDateTime
	 */
	public String getCreateDateTime() {
		return createDateTime;
	}
	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	/**
	 * @return the updateDateTime
	 */
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	/**
	 * @param updateDateTime the updateDateTime to set
	 */
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
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
	/**
	 * @param id
	 * @param globalId
	 * @param createDateTime
	 * @param updateDateTime
	 * @param error
	 * @param success
	 */
	public PatientResponse(int id, int globalId, String createDateTime,
			String updateDateTime, ErrorDTO error, boolean success) {
		super();
		this.id = id;
		this.globalId = globalId;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
		this.error = error;
		this.success = success;
	}
	/**
	 * 
	 */
	public PatientResponse() {
		
	}
	

}
