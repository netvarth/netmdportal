/**
 * ScheduleResponseDTO.java
 *
 * @Author Luciya Jos
 * May 18, 2013 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author netvarth
 *
 */
public class ScheduleResponseDTO {
	private int id;
	private int globalId;
	private String createDateTime;
	private String updateDateTime;
	private ErrorDTO error;
	private boolean success;
	private int seriesId;
	private int seriesGlobalId;
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
	 * @return the globalId
	 */
	public int getGlobalId() {
		return globalId;
	}
	/**
	 * @param globalId the globalId to set
	 */
	public void setGlobalId(int globalId) {
		this.globalId = globalId;
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
	 * @return the seriesId
	 */
	public int getSeriesId() {
		return seriesId;
	}
	/**
	 * @param seriesId the seriesId to set
	 */
	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}
	/**
	 * @return the seriesGlobalId
	 */
	public int getSeriesGlobalId() {
		return seriesGlobalId;
	}
	/**
	 * @param seriesGlobalId the seriesGlobalId to set
	 */
	public void setSeriesGlobalId(int seriesGlobalId) {
		this.seriesGlobalId = seriesGlobalId;
	}
	/**
	 * @param id
	 * @param globalId
	 * @param createDateTime
	 * @param updateDateTime
	 * @param error
	 * @param success
	 * @param seriesId
	 * @param seriesGlobalId
	 */
	public ScheduleResponseDTO(int id, int globalId, String createDateTime,
			String updateDateTime, ErrorDTO error, boolean success,
			int seriesId, int seriesGlobalId) {
		super();
		this.id = id;
		this.globalId = globalId;
		this.createDateTime = createDateTime;
		this.updateDateTime = updateDateTime;
		this.error = error;
		this.success = success;
		this.seriesId = seriesId;
		this.seriesGlobalId = seriesGlobalId;
	}
	/**
	 * 
	 */
	public ScheduleResponseDTO() {
		super();
		
	}
	

}
