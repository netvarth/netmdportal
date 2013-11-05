package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the sync_log_tbl database table.
 * 
 */
@Entity
@Table(name="sync_log_tbl")
public class SyncLogTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;


	@Column(name="application_name", nullable=false, length=45)
	private String applicationName;

	@Column(name="branch_name", nullable=false, length=45)
	private String branchName;

	@Column(name="head_office_name", nullable=false, length=45)
	private String headOfficeName;

	@Column(name="ip_address", nullable=false, length=45)
	private String ipAddress;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_sync_time", nullable=false)
	private Date lastSyncTime;

	public SyncLogTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getApplicationName() {
		return this.applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getHeadOfficeName() {
		return this.headOfficeName;
	}

	public void setHeadOfficeName(String headOfficeName) {
		this.headOfficeName = headOfficeName;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getLastSyncTime() {
		return this.lastSyncTime;
	}

	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

}