package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

public class BranchBillListResponseDTO {
	private List<BillSummaryDTO> branchBillList=new ArrayList<BillSummaryDTO>();
	private String fromDate;
	private String toDate;
	private ErrorDTO error;
	private boolean success;
	private double totalBillAmt;
	private double totalAmtPaid;
	private double totalAmtDue;
	
	
	public double getTotalBillAmt() {
		return totalBillAmt;
	}
	public void setTotalBillAmt(double totalBillAmt) {
		this.totalBillAmt = totalBillAmt;
	}
	public double getTotalAmtPaid() {
		return totalAmtPaid;
	}
	public void setTotalAmtPaid(double totalAmtPaid) {
		this.totalAmtPaid = totalAmtPaid;
	}
	public double getTotalAmtDue() {
		return totalAmtDue;
	}
	public void setTotalAmtDue(double totalAmtDue) {
		this.totalAmtDue = totalAmtDue;
	}
	private Long count;
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
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
