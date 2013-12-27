package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class SyncLogListResponseDTO {
	private List<SyncLogDetail> syncLog = new ArrayList<SyncLogDetail>();
	private Long count;
	private ErrorDTO error;
	private boolean success;

	
	public List<SyncLogDetail> getSyncLog() {
		return syncLog;
	}
	public void setSyncLog(List<SyncLogDetail> syncLog) {
		this.syncLog = syncLog;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	

}
