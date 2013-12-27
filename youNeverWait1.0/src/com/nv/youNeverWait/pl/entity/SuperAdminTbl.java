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

	@Column(name="enable_log", nullable=false)
	private boolean enableLog;

	@Column(name="enable_sync_log", nullable=false)
	private boolean enableSyncLog;

	@Column(nullable=false, length=45)
	private String name;

	@Column(nullable=false, length=45)
	private String password;

	@Column(name="sync_freq_type", nullable=false, length=45)
	private String syncFreqType;

	@Column(name="sync_time", nullable=false)
	private int syncTime;

	@Column(name="user_name", nullable=false, length=45)
	private String userName;

	@Column(name="enable_sync", nullable=false)
	private boolean enableSync;
	
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

	public boolean getEnableLog() {
		return this.enableLog;
	}

	public void setEnableLog(boolean enableLog) {
		this.enableLog = enableLog;
	}

	public boolean getEnableSyncLog() {
		return this.enableSyncLog;
	}

	public void setEnableSyncLog(boolean enableSyncLog) {
		this.enableSyncLog = enableSyncLog;
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

	public String getSyncFreqType() {
		return this.syncFreqType;
	}

	public void setSyncFreqType(String syncFreqType) {
		this.syncFreqType = syncFreqType;
	}

	public int getSyncTime() {
		return this.syncTime;
	}

	public void setSyncTime(int syncTime) {
		this.syncTime = syncTime;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean getEnableSync() {
		return this.enableSync;
	}

	public void setEnableSync(boolean enableSync) {
		this.enableSync = enableSync;
	}

}