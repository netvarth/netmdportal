/**
 * UserDetails.java
 */
package com.nv.youNeverWait.rs.dto;

public class UserDetails {
	private String name;
	private String userName;
	private Integer id;
	private int labId;
	private int netmdId;
	private int netrxId;
	private int organisationId;
	private String accountName;
	private String userType;
	
	
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getOrganisationId() {
		return organisationId;
	}
	public void setOrganisationId(int organisationId) {
		this.organisationId = organisationId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the labId
	 */
	public int getLabId() {
		return labId;
	}
	/**
	 * @param labId the labId to set
	 */
	public void setLabId(int labId) {
		this.labId = labId;
	}
	
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return the netmdId
	 */
	public int getNetmdId() {
		return netmdId;
	}
	/**
	 * @param netmdId the netmdId to set
	 */
	public void setNetmdId(int netmdId) {
		this.netmdId = netmdId;
	}
	public int getNetrxId() {
		return netrxId;
	}
	public void setNetrxId(int netrxId) {
		this.netrxId = netrxId;
	}
}
