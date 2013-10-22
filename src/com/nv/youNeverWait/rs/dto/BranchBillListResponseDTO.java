package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class BranchBillListResponseDTO {
	private List<BillSummaryDTO> branchBillList=new ArrayList<BillSummaryDTO>();
	private ErrorDTO error;
	private boolean success;
	public List<BillSummaryDTO> getbranchBillList() {
		return branchBillList;
	}
	public void setbranchBillList(List<BillSummaryDTO> branchBillList) {
		this.branchBillList = branchBillList;
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
