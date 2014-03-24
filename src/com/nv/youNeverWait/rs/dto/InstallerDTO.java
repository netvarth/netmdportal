package com.nv.youNeverWait.rs.dto;

public class InstallerDTO {
	private int id;
	private String applicationName;
	private String warFilePath;
	private String query;
	private Float versionNo;
	/**
	 * 
	 */
	public InstallerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String createdDate;
	
	/**
	 * @param id
	 * @param applicationName
	 * @param warFilePath
	 * @param query
	 * @param versionNo
	 * @param createdDate
	 */
	public InstallerDTO(int id, String applicationName, String warFilePath,
			String query, Float versionNo, String createdDate) {
		super();
		this.id = id;
		this.applicationName = applicationName;
		this.warFilePath = warFilePath;
		this.query = query;
		this.versionNo = versionNo;
		this.createdDate = createdDate;
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
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}
	/**
	 * @param applicationName the applicationName to set
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	/**
	 * @return the warFilePath
	 */
	public String getWarFilePath() {
		return warFilePath;
	}
	/**
	 * @param warFilePath the warFilePath to set
	 */
	public void setWarFilePath(String warFilePath) {
		this.warFilePath = warFilePath;
	}
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	/**
	 * @return the versionNo
	 */
	public Float getVersionNo() {
		return versionNo;
	}
	/**
	 * @param versionNo the versionNo to set
	 */
	public void setVersionNo(Float versionNo) {
		this.versionNo = versionNo;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
