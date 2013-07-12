/**
 * BranchOrderDetail.java
 *
 * @Author Luciya Jos
 * Jun 24, 2013 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author netvarth
 *
 */
public class BranchOrderDetail {
	private int id;
	private String branchName;
	private int branchId;
	private int totalOrders;
	private float paidAmount;
	private float netAmount;
	private String lastOrderdTime;
	private String orderDate;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the totalOrders
	 */
	public int getTotalOrders() {
		return totalOrders;
	}
	/**
	 * @param totalOrders the totalOrders to set
	 */
	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}
	
	/**
	 * @return the paidAmount
	 */
	public float getPaidAmount() {
		return paidAmount;
	}
	/**
	 * @param paidAmount the paidAmount to set
	 */
	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}
	/**
	 * @return the netAmount
	 */
	public float getNetAmount() {
		return netAmount;
	}
	/**
	 * @param netAmount the netAmount to set
	 */
	public void setNetAmount(float netAmount) {
		this.netAmount = netAmount;
	}
	/**
	 * @return the lastOrderdTime
	 */
	public String getLastOrderdTime() {
		return lastOrderdTime;
	}
	/**
	 * @param lastOrderdTime the lastOrderdTime to set
	 */
	public void setLastOrderdTime(String lastOrderdTime) {
		this.lastOrderdTime = lastOrderdTime;
	}

}
