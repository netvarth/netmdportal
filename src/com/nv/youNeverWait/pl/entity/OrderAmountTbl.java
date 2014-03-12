package com.nv.youNeverWait.pl.entity;


import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the order_amount_tbl database table.
 * 
 */
@Entity
@Table(name="order_amount_tbl")
public class OrderAmountTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="last_ordered_time",nullable = false)
	private Date lastOrderedTime;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="order_date",nullable=false)
	private Date orderDate;
    
	@Column(name="net_amount",nullable=false)
	private float netAmount;

	@Column(name="paid_amount",nullable=false)
	private float paidAmount;

	@Column(name="total_orders",nullable=false)
	private int totalOrders;

	//bi-directional many-to-one association to LabBranchTbl
    @ManyToOne
	@JoinColumn(name="lab_branch_id",nullable=false)
	private LabBranchTbl labBranchTbl;

	//bi-directional many-to-one association to LabTbl
    @ManyToOne
	@JoinColumn(name="lab_id",nullable=false)
	private LabTbl labTbl;

    public OrderAmountTbl() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLastOrderedTime() {
		return this.lastOrderedTime;
	}

	public void setLastOrderedTime(Date lastOrderedTime) {
		this.lastOrderedTime = lastOrderedTime;
	}

	public float getNetAmount() {
		return this.netAmount;
	}

	public void setNetAmount(float netAmount) {
		this.netAmount = netAmount;
	}

	public float getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}


	public int getTotalOrders() {
		return this.totalOrders;
	}

	public void setTotalOrders(int totalOrders) {
		this.totalOrders = totalOrders;
	}

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}
	
	public LabTbl getLabTbl() {
		return this.labTbl;
	}

	public void setLabTbl(LabTbl labTbl) {
		this.labTbl = labTbl;
	}
	
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}