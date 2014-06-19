package com.nv.NetlimsPortal.pl.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the order_transfer_tbl database table.
 * 
 */
@Entity
@Table(name="order_transfer_tbl")
public class OrderTransferTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date_time", nullable=false)
	private Date createdDateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date_time", nullable=false)
	private Date updatedDateTime;

	//bi-directional many-to-one association to LabBranchTbl
	@ManyToOne
	@JoinColumn(name="destination_branch_id", nullable=false)
	private LabBranchTbl labBranchTbl;

	//bi-directional many-to-one association to LabTbl
	@ManyToOne
	@JoinColumn(name="destination_lab_id", nullable=false)
	private LabTbl labTbl;

	//bi-directional many-to-one association to OrderBranchTbl
	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	private OrderBranchTbl orderBranchTbl;

	@Column(name="sent")
	private boolean sent;
	
	
	public OrderTransferTbl() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedDateTime() {
		return this.createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getUpdatedDateTime() {
		return this.updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
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

	public OrderBranchTbl getOrderBranchTbl() {
		return this.orderBranchTbl;
	}

	public void setOrderBranchTbl(OrderBranchTbl orderBranchTbl) {
		this.orderBranchTbl = orderBranchTbl;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

}