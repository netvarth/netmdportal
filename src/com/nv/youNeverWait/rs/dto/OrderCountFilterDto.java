package com.nv.youNeverWait.rs.dto;

public class OrderCountFilterDto {

	private Integer branch;
	private Integer facility;
	private String fromDate;
	private String toDate;
	public Integer getBranch() {
		return branch;
	}
	public void setBranch(Integer branch) {
		this.branch = branch;
	}
	public Integer getFacility() {
		return facility;
	}
	public void setFacility(Integer facility) {
		this.facility = facility;
	}
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
	
	
}
