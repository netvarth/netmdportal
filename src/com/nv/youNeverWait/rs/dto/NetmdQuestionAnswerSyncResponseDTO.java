/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author net varth
 *
 */
public class NetmdQuestionAnswerSyncResponseDTO {
	private int globalId;
	private int id;
	private boolean success;
	private ErrorDTO error;
	private String actionName;
	private String createDateTime;
	private String updateDateTime;
	
	
	
	public int getGlobalId() {
		return globalId;
	}
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getUpdateDateTime() {
		return updateDateTime;
	}
	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

}
