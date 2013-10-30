package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lab_tbl database table.
 * 
 */
@Entity
@Table(name="log_tbl")
public class LogTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="ip_address", length=45)
	private String ipAddress;

	@Column(name="user_name", length=45)
	private String userName;

	@Column(name="action_name", length=45)
	private String actionName;
	
	@Column(name="action_time", length=45)
	private String actionTime;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="action_date")
	private Date actionDate;

	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="login_time")
	private Date loginTime;


    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="logout_time")
	private Date logoutTime;
    
    @Column(nullable=false,name="application_name", length=100)
	private String applicationName;
    
    @Column(name="user_type", length=45)
   	private String userType;

    
    public LogTbl() {
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
	 * @return the loginTime
	 */
	public Date getLoginTime() {
		return loginTime;
	}


	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}


	/**
	 * @return the logoutTime
	 */
	public Date getLogoutTime() {
		return logoutTime;
	}


	/**
	 * @param logoutTime the logoutTime to set
	 */
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
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
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}


	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	/**
	 * @return the actionTime
	 */
	public String getActionTime() {
		return actionTime;
	}


	/**
	 * @param actionTime the actionTime to set
	 */
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}


	/**
	 * @return the actionDate
	 */
	public Date getActionDate() {
		return actionDate;
	}


	/**
	 * @param actionDate the actionDate to set
	 */
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	
	
}