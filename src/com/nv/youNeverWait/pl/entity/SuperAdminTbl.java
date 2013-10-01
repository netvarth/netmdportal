package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the super_admin_tbl database table.
 * 
 */
@Entity
@Table(name="super_admin_tbl")
public class SuperAdminTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String email;

	@Column(nullable=false, length=45)
	private String name;

	@Column(nullable=false, length=45)
	private String password;

	@Column(name="user_name", nullable=false, length=45)
	private String userName;
	
	@Column(name="enable_log")
	private boolean enableLog;

	@Column(name="enable_sync_log", nullable=false)
	private boolean enableSyncLog;
	
    public SuperAdminTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the enableLog
	 */
	public boolean isEnableLog() {
		return enableLog;
	}

	/**
	 * @param enableLog the enableLog to set
	 */
	public void setEnableLog(boolean enableLog) {
		this.enableLog = enableLog;
	}

	/**
	 * @return the enableSyncLog
	 */
	public boolean isEnableSyncLog() {
		return enableSyncLog;
	}

	/**
	 * @param enableSyncLog the enableSyncLog to set
	 */
	public void setEnableSyncLog(boolean enableSyncLog) {
		this.enableSyncLog = enableSyncLog;
	}

}