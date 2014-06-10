package com.nv.youNeverWait.rs.dto;

import com.nv.youNeverWait.pl.entity.SyncLogTbl;

public class SyncLogDetail {
	private int id;
	private String applicationName;
	private String branchName;
	private String headOfficeName;
	private String ipAddress;
	private String lastSyncTime;
	
	public SyncLogDetail(SyncLogTbl synclogTbl, String lastSyncTime) {
		this.setId(synclogTbl.getId());
		this.setApplicationName(synclogTbl.getApplicationName());
		this.setBranchName(synclogTbl.getBranchName());
		this.setHeadOfficeName(synclogTbl.getHeadOfficeName());
		this.setIpAddress(synclogTbl.getIpAddress());
		this.setLastSyncTime(lastSyncTime);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getHeadOfficeName() {
		return headOfficeName;
	}
	public void setHeadOfficeName(String headOfficeName) {
		this.headOfficeName = headOfficeName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(String lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}
	
	

}
