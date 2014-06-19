/**
 * BranchOrderDetail.java
 *
 * @Author Luciya Jos
 * Jun 24, 2013 
 */
package com.nv.NetlimsPortal.rs.dto;

import java.text.SimpleDateFormat;

import com.nv.NetlimsPortal.general.Constants;
import com.nv.NetlimsPortal.pl.entity.OrderAmountTbl;



/**
 * @author Luciya Jos
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
	/**
	 * @param id
	 * @param branchName
	 * @param branchId
	 * @param totalOrders
	 * @param paidAmount
	 * @param netAmount
	 * @param lastOrderdTime
	 * @param orderDate
	 */
	public BranchOrderDetail(OrderAmountTbl orderAmt) {
		SimpleDateFormat sdf= new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		this.id = orderAmt.getId();
		this.branchName = orderAmt.getLabBranchTbl().getName();
		this.branchId = orderAmt.getLabBranchTbl().getId();
		this.totalOrders = orderAmt.getTotalOrders();
		this.paidAmount = orderAmt.getPaidAmount();
		this.netAmount = orderAmt.getNetAmount();
		this.lastOrderdTime = sdf.format(orderAmt.getLastOrderedTime());
		this.orderDate = sdf.format(orderAmt.getOrderDate());
	}
	/**
	 * 
	 */
	public BranchOrderDetail() {
		// TODO Auto-generated constructor stub
	}

}
