package com.nv.youNeverWait.pl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;




/**
 * The persistent class for the order_transfer_count_tbl database table.
 * 
 */
@Entity
@Table(name="order_transfer_count_tbl")
@NamedQuery(name="OrderTransferCountTbl.findAll", query="SELECT o FROM OrderTransferCountTbl o")
public class OrderTransferCountTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name="order_count")
	private int orderCount;

	@Column(name="order_hit_count")
	private int orderHitCount;

	//bi-directional many-to-one association to LabFacilityTbl
	@ManyToOne
	@JoinColumn(name="facility_id")
	private LabFacilityTbl labFacilityTbl;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="branch_id")
	private LabBranchTbl labBranchTbl;

	public OrderTransferCountTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getOrderCount() {
		return this.orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getOrderHitCount() {
		return this.orderHitCount;
	}

	public void setOrderHitCount(int orderHitCount) {
		this.orderHitCount = orderHitCount;
	}

	public LabFacilityTbl getLabFacilityTbl() {
		return this.labFacilityTbl;
	}

	public void setLabFacilityTbl(LabFacilityTbl labFacilityTbl) {
		this.labFacilityTbl = labFacilityTbl;
	}

	public LabBranchTbl getLabBranchTbl() {
		return this.labBranchTbl;
	}

	public void setLabBranchTbl(LabBranchTbl labBranchTbl) {
		this.labBranchTbl = labBranchTbl;
	}

}