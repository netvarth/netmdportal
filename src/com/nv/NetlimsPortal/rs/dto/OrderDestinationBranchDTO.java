package com.nv.NetlimsPortal.rs.dto;

public class OrderDestinationBranchDTO {
	private String destinationBranch;
	private boolean orderSent;
	public String getDestinationBranch() {
		return destinationBranch;
	}
	public void setDestinationBranch(String destinationBranch) {
		this.destinationBranch = destinationBranch;
	}
	public boolean isOrderSent() {
		return orderSent;
	}
	public void setOrderSent(boolean orderSent) {
		this.orderSent = orderSent;
	}
	

}
