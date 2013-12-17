package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class BranchBillListResponseDTO {
	private List<BillSummaryDTO> branchBillList=new ArrayList<BillSummaryDTO>();
	private ErrorDTO error;
	private boolean success;
	private Long count;
	
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
	/**
	 * @return the branchBillList
	 */
	public List<BillSummaryDTO> getBranchBillList() {
		return branchBillList;
	}
	/**
	 * @param branchBillList the branchBillList to set
	 */
	public void setBranchBillList(List<BillSummaryDTO> branchBillList) {
		this.branchBillList = branchBillList;
	}
	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
	
	
}
